package com.jtanveer.raydar.login.model;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

import com.jtanveer.raydar.BR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginForm extends BaseObservable {

    private LoginFields fields = new LoginFields();
    private LoginErrorFields errors = new LoginErrorFields();
    private LoginStatus loginStatus = new LoginStatus();
    private MutableLiveData<LoginStatus> buttonClick = new MutableLiveData<>();

    public boolean isEmpty() {
        String email = fields.getEmail();
        String password = fields.getPassword();
        boolean emptyEmail = email == null || TextUtils.isEmpty(email);
        boolean emptyPassword = password == null || TextUtils.isEmpty(password);
        return  emptyEmail || emptyPassword;
    }

    public boolean isValid(boolean setMessage) {
        boolean emailValid = isEmailValid(setMessage);
        boolean passwordValid = isPasswordValid(setMessage);
        return emailValid && passwordValid;
    }

    public boolean isEmailValid(boolean setMessage) {
        String email = fields.getEmail();
        if (email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors.setEmail(null);
            notifyPropertyChanged(BR.emailError);
            return true;
        } else {
            if (setMessage) {
                errors.setEmail("Email is not valid");
                notifyPropertyChanged(BR.emailError);
            }
            return false;
        }

    }

    public boolean isPasswordValid(boolean setMessage) {
        String password = fields.getPassword();

        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);

        if (password != null && !TextUtils.isEmpty(password) && matcher.matches()) {
            errors.setPassword(null);
            notifyPropertyChanged(BR.passwordError);
            return true;
        } else {
            if (setMessage) {
                errors.setPassword("Password should contain one special character and minimum 8 character required");
                notifyPropertyChanged(BR.passwordError);
            }
            return false;
        }
    }

    public void onClick() {
        if (isEmpty()) {
            loginStatus.setSuccess(false);
            loginStatus.setId(0);
            loginStatus.setMessage("Please complete the form");
        } else if (!isValid(true)) {
            loginStatus.setSuccess(false);
            loginStatus.setId(0);
            loginStatus.setMessage(null);
        } else {
            loginStatus.setSuccess(true);
            loginStatus.setId(100);
            loginStatus.setMessage(null);
        }
        buttonClick.setValue(loginStatus);
    }

    public MutableLiveData<LoginStatus> getLoginStatus() {
        return buttonClick;
    }

    public LoginFields getFields() {
        return fields;
    }

    @Bindable
    public String getEmailError() {
        return errors.getEmail();
    }

    @Bindable
    public String getPasswordError() {
        return errors.getPassword();
    }
}
