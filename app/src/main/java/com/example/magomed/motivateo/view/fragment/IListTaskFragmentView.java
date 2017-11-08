package com.example.magomed.motivateo.view.fragment;

import com.example.magomed.motivateo.models.Message;
import com.example.magomed.motivateo.models.Task;

import java.util.List;


public interface IListTaskFragmentView {
    Message getUserInformation();
    void setListTaskAdapter(List<Task> itemArtists);
}
