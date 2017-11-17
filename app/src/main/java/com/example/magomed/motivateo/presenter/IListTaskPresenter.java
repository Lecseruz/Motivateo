package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.view.fragment.IListTaskFragmentView;

public interface IListTaskPresenter {
    void onCreate(IListTaskFragmentView view);
    void onResume();
    void onItemClick(int position);
}
