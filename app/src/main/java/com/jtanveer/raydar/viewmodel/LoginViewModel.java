package com.jtanveer.raydar.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.jtanveer.raydar.lifecycle.SingleLiveEvent;
import com.jtanveer.raydar.repository.UserRepository;
import com.jtanveer.raydar.form.LoginForm;
import com.jtanveer.raydar.validation.ValidationStatus;
import com.jtanveer.raydar.validation.field.UserFields;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private LoginForm login;

    @Inject
    UserRepository userRepository;

    @Inject
    public LoginViewModel() {
    }

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

    public SingleLiveEvent<ValidationStatus> getValidationStatus() {
        return login.getValidationStatus();
    }

    public LiveData<Long> getLoginStatus() {
        UserFields fields = login.getFields();
        return userRepository.login(fields.getEmail(), fields.getPassword());
    }
}
