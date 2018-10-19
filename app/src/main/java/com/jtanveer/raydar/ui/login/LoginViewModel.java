package com.jtanveer.raydar.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.jtanveer.raydar.ui.login.model.LoginForm;
import com.jtanveer.raydar.ui.login.model.LoginStatus;

public class LoginViewModel extends ViewModel {
    private LoginForm login;

    @VisibleForTesting
    public void init() {
        login = new LoginForm();
    }

    public LoginForm getLogin() {
        return login;
    }

    public void onButtonClick() {
        login.onClick();
    }

    public MutableLiveData<LoginStatus> getLoginStatus() {
        return login.getLoginStatus();
    }
}
