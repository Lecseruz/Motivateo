package com.example.magomed.motivateo.presenter;

import android.support.annotation.Nullable;

public class ListenerHandler<T> {
    private T listener;

    public ListenerHandler(final T listener) {
        this.listener = listener;
    }

    @Nullable
    public T getListener() {
        return listener;
    }

    public void unregister() {
        listener = null;
    }
}
