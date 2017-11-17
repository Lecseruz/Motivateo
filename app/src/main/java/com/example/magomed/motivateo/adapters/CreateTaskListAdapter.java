package com.example.magomed.motivateo.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.magomed.motivateo.R;

import java.util.List;

public class CreateTaskListAdapter extends RecyclerView.Adapter<CreateTaskListAdapter.ViewHolder> {

    private List<Integer> itemArtistList;
    private LayoutInflater layoutInflater;

    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_ACTIVITY = 1;

    public CreateTaskListAdapter(FragmentActivity activity, List<Integer> itemArtists) {
        this.layoutInflater = activity.getLayoutInflater();
        this.itemArtistList = itemArtists;
    }

    @Override
    public CreateTaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list, null);

        return new CreateTaskListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreateTaskListAdapter.ViewHolder holder, int position) {
        holder.item.setText(itemArtistList.get(position).toString());
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
        TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
        }
    }
}
