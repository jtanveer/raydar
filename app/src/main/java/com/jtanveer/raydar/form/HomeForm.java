package com.jtanveer.raydar.form;

import android.databinding.BaseObservable;

import com.jtanveer.raydar.lifecycle.SingleLiveEvent;

public class HomeForm extends BaseObservable {

    private SingleLiveEvent<String> mobileLiveData = new SingleLiveEvent<>();
    private SingleLiveEvent<String> userTypeLiveData = new SingleLiveEvent<>();

    public void onEditButtonClick(String mobile) {
        mobileLiveData.setValue(mobile);
    }

    public void onTypeButtonClick(String userType) {
        userTypeLiveData.postValue(userType);
    }

    public SingleLiveEvent<String> getEditButtonClickStatus() {
        return mobileLiveData;
    }

    public SingleLiveEvent<String> getTypeButtonClickStatus() {
        return userTypeLiveData;
    }
}
