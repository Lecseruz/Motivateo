package com.example.magomed.motivateo.view.fragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.adapters.CreateTaskListAdapter;
import com.example.magomed.motivateo.fragments.BaseFragment;
import com.example.magomed.motivateo.models.Task;
import com.example.magomed.motivateo.presenter.CreateTaskFragmentPresenterImpl;
import com.example.magomed.motivateo.presenter.ICreateTaskFragmentPresenter;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

public class CreateTaskFragment extends BaseFragment implements ICreateFragmentTask{
    @BindView(R.id.task_name)
    EditText task_name;

    List<Integer> itemArtistCount;

    List<Integer> itemArtistType;

    private CreateTaskListAdapter adapter_count;

    private CreateTaskListAdapter adapter_type;

    TextView typeTextView;

    TextView countTextView;

    TextView timeTextView;

    TextView repeatTextView;

    ICreateTaskFragmentPresenter presenter;

    RecyclerView recyclerView_count;

    RecyclerView recyclerView_type;

    @BindView(R.id.button_count)
    Button count;

    @BindView(R.id.button_save)
    Button save;

    Switch switch_remind;

    FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter =  new CreateTaskFragmentPresenterImpl(getActivity());
        presenter.onCreate(this);
    }

    public CreateTaskFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_create_task, container, false);
        ButterKnife.bind(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        timeTextView = view.findViewById(R.id.time_text_view);
        repeatTextView = view.findViewById(R.id.repeat_text_view);
        countTextView = view.findViewById(R.id.count_text_view);
        typeTextView = view.findViewById(R.id.type_text_view);
        switch_remind = view.findViewById(R.id.create_task_switch);
        fragmentManager = getActivity().getSupportFragmentManager();
        recyclerView_count = view.findViewById(R.id.count_recyclerView);
        recyclerView_count.setLayoutManager(layoutManager);
        recyclerView_count.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView_count, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                countTextView.setText("count " + itemArtistCount.get(position).toString());
                if (countTextView.getVisibility() != View.VISIBLE){
                    countTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView_type = view.findViewById(R.id.type_recyclerView);
        recyclerView_type.setLayoutManager(layoutManager);
        recyclerView_type.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView_type, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                typeTextView.setText("type " + itemArtistType.get(position).toString());
                if (typeTextView.getVisibility() != View.VISIBLE){
                    typeTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        view.findViewById(R.id.button_set_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeTextView.setText(selectedHour + ":" + selectedMinute);
                        if (timeTextView.getVisibility() != View.VISIBLE){
                            timeTextView.setVisibility(View.VISIBLE);
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        view.findViewById(R.id.button_repeat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("GOOD")
                        .setItems(R.array.array_type_repeat, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                repeatTextView.setText(getResources().getStringArray(R.array.array_type_repeat)[which]);
                                if (repeatTextView.getVisibility() != View.VISIBLE){
                                    repeatTextView.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        view.findViewById(R.id.button_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadTaskList();
            }
        });

        view.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final TextView textViewInfo = view.findViewById(R.id.error_text_view);

                if (isEmpty()) {
                    textViewInfo.setText("Error data");
                } else {
                    presenter.sendTask(new Task(task_name.getText().toString(),
                            timeTextView.getText().toString(),
                            repeatTextView.getText().toString(),
                            switch_remind.getShowText(),
                            typeTextView.getText().toString(),
                            countTextView.getText().toString()));
                }
            }
        });

        return view;
    }

    @Override
    public void setListTaskAdapter(List<Integer> itemArtistCount, List<Integer> itemArtistType) {
        if (adapter_count == null) {
            this.itemArtistCount = itemArtistCount;
            adapter_count = new CreateTaskListAdapter(getActivity(), itemArtistCount);
            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter_count);
            animationAdapter.setFirstOnly(false);
            animationAdapter.setDuration(500);
            recyclerView_count.setAdapter(animationAdapter);
        }

        if (adapter_type == null) {
            this.itemArtistType = itemArtistType;
            adapter_type = new CreateTaskListAdapter(getActivity(), itemArtistType);
            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter_type);
            animationAdapter.setFirstOnly(false);
            animationAdapter.setDuration(500);
            recyclerView_type.setAdapter(animationAdapter);
        }

        getActivity().findViewById(R.id.body_set_count).setVisibility(View.VISIBLE);
    }

    public boolean isEmpty() {
        return repeatTextView.getText().equals("") && countTextView.getText().equals("") && typeTextView.getText().equals("") && timeTextView.getText().equals("") && Objects.equals(task_name.getText().toString(), "");
    }

    @Override
    public void popFragmentFromStack() {
        fragmentManager.popBackStack();
    }


    interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    private static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private CreateTaskFragment.ClickListener clickListener;

        RecyclerTouchListener(Context context, final RecyclerView recyclerView, final CreateTaskFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
