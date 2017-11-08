package com.example.magomed.motivateo.adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.magomed.motivateo.R;
import com.example.magomed.motivateo.models.Task;

import java.util.List;


public class TodayListTaskAdapter extends RecyclerView.Adapter<TodayListTaskAdapter.ViewHolder>{
    private List<Task> itemArtistList;
    private LayoutInflater layoutInflater;

    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_ACTIVITY = 1;

    public TodayListTaskAdapter(Activity activity,  List<Task> itemArtistList) {
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemArtistList = itemArtistList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_task_list, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = itemArtistList.get(position);
        holder.button.setText(task.getName());
    }

    @Override
    public int getItemViewType(int position) {
        return (position >= itemArtistList.size()) ? VIEW_TYPE_LOADING : VIEW_TYPE_ACTIVITY;
    }

    @Override
    public long getItemId(int position) {
        return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? position : -1;
    }

    @Override
    public int getItemCount() {
        return itemArtistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.task_name);
        }
    }
}
