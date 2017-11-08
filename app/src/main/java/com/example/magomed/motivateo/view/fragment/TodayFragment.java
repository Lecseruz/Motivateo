package com.example.magomed.motivateo.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.adapters.TodayListTaskAdapter;
import com.example.magomed.motivateo.fragments.BaseFragment;
import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.Task;
import com.example.magomed.motivateo.presenter.ListTodayTaskPresenterImpl;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

public class TodayFragment extends BaseFragment implements IListTaskFragmentView {
    private TodayListTaskAdapter adapter;
    private AppCompatActivity activity;
    private RecyclerView recyclerView;
    private ListTodayTaskPresenterImpl presenter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new FadeInDownAnimator());
        return view;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new ListTodayTaskPresenterImpl();
        if (context instanceof Activity) {
            activity = (AppCompatActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter.onCreate(this);
    }

    @Override
    public Message getUserInformation() {
        return null;
    }

    @Override
    public void setListTaskAdapter(List<Task> itemArtists) {
        if (adapter == null) {
            adapter = new TodayListTaskAdapter(getActivity(), itemArtists);
            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
            animationAdapter.setFirstOnly(false);
            animationAdapter.setDuration(500);
            recyclerView.setAdapter(animationAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }
}
