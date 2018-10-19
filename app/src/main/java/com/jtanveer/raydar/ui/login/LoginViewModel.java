package com.jtanveer.raydar.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.jtanveer.raydar.database.model.User;
import com.jtanveer.raydar.repository.UserRepository;
import com.jtanveer.raydar.ui.login.model.LoginForm;
import com.jtanveer.raydar.validation.ValidationStatus;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private LoginForm login;
    @Inject
    public UserRepository userRepository;

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

    public MutableLiveData<ValidationStatus> getValidationStatus() {
        return login.getValidationStatus();
    }

    public LiveData<User> getLoginStatus() {
        return userRepository.getUser(login.getFields().getEmail());
    }
}
