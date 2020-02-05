package com.efremov.weather.base.model.live_data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class EventLiveData extends LiveData<Object> {

    private final int mSubject;

    public EventLiveData(@LiveDataBus.Subject int subject) {
        mSubject = subject;
    }

    public void update(Object object) {
        postValue(object);
    }

    @Override
    public void removeObservers(@NonNull LifecycleOwner owner) {
        super.removeObservers(owner);
        if (!hasObservers()) {
            LiveDataBus.unregister(mSubject);
        }
    }
}
