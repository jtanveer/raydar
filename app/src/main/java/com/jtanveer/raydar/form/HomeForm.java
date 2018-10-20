package com.jtanveer.raydar.form;

import android.databinding.BaseObservable;

import com.jtanveer.raydar.lifecycle.SingleLiveEvent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeForm extends BaseObservable {

    private SingleLiveEvent<String> mobileLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<String> userTypeLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<Boolean> logoutLiveData = new SingleLiveEvent<>();

    public void onEditButtonClick(String mobile) {
        mobileLiveData.setValue(mobile);
    }

    public void onTypeButtonClick(String userType) {
        userTypeLiveData.postValue(userType);
    }

    public void onLogoutButtonClick(ScheduledExecutorService executor) {
        executor.schedule(() -> logoutLiveData.postValue(true), 5, TimeUnit.SECONDS);
    }

    public SingleLiveEvent<String> getEditButtonClickStatus() {
        return mobileLiveData;
    }

    public SingleLiveEvent<String> getTypeButtonClickStatus() {
        return userTypeLiveData;
    }

    public SingleLiveEvent<Boolean> getLogoutButtonClickStatus() {
        return logoutLiveData;
    }
}
