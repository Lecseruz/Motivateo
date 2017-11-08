package com.example.magomed.motivateo.presenter;

import com.example.magomed.motivateo.view.fragment.IListTaskFragmentView;

/**
 * Created by magomed on 01.11.17.
 */

public interface IListTaskPresenter {
    void onCreate(IListTaskFragmentView view);
    void onResume();
    void onPause();
    void onItemClick(int position);
}
