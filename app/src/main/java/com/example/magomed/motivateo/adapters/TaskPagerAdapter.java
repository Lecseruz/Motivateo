package com.example.magomed.motivateo.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magomed.motivateo.models.TaskObject;

public class TaskPagerAdapter extends PagerAdapter {

    private Context mContext;

    public TaskPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        TaskObject taskObject = TaskObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(taskObject.getFragment(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return TaskObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
}
