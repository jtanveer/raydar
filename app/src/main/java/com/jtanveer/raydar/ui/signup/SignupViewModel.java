package com.jtanveer.raydar.ui.signup;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.jtanveer.raydar.ui.signup.model.SignupForm;
import com.jtanveer.raydar.ui.signup.model.SignupStatus;

public class SignupViewModel extends ViewModel {
    private SignupForm signup;

    @VisibleForTesting
    public void init() {
        signup = new SignupForm();
    }

    public SignupForm getSignup() {
        return signup;
    }

    public void onButtonClick() {
        signup.onClick();
    }

    public MutableLiveData<SignupStatus> getSignupStatus() {
        return signup.getSignupStatus();
    }
}
