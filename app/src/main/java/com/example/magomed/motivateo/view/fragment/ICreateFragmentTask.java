package com.example.magomed.motivateo.view.fragment;

import java.util.List;


public interface ICreateFragmentTask {
    void popFragmentFromStack();
    void setListTaskAdapter(List<Integer> itemArtistCount, List<Integer> itemArtistType);
}