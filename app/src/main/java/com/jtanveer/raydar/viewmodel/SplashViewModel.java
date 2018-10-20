package com.jtanveer.raydar.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.jtanveer.raydar.lifecycle.SingleLiveEvent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class SplashViewModel extends ViewModel {

    @Inject
    ScheduledExecutorService executor;

    @Inject
    public SplashViewModel() {
    }

    public SingleLiveEvent<Boolean> getSplashScreenTimerUpdate() {
        SingleLiveEvent<Boolean> data = new SingleLiveEvent<>();
        executor.schedule(() -> data.postValue(true), 5, TimeUnit.SECONDS);
        return data;
    }
}
