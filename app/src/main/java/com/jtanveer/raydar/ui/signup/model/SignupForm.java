package com.jtanveer.raydar.ui.signup.model;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

import com.jtanveer.raydar.BR;
import com.jtanveer.raydar.validation.InputValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupForm extends BaseObservable {

    private SignupFields fields = new SignupFields();
    private SignupErrorFields errors = new SignupErrorFields();
    private SignupStatus signupStatus = new SignupStatus();
    private MutableLiveData<SignupStatus> buttonClick = new MutableLiveData<>();
    private InputValidator validator = new InputValidator();

    public boolean isEmpty() {
        boolean emptyEmail = isEmptyField(fields.getEmail());
        boolean emptyPassword = isEmptyField(fields.getPassword());
        boolean emptyMobile = isEmptyField(fields.getMobile());
        return  emptyEmail || emptyPassword || emptyMobile;
    }

    private boolean isEmptyField(String field) {
        return field == null || TextUtils.isEmpty(field);
    }

    public boolean isValid(boolean setMessage) {
        boolean emailValid = isEmailValid(setMessage);
        boolean passwordValid = isPasswordValid(setMessage);
        boolean mobileValid = isMobileValid(setMessage);
        return emailValid && passwordValid && mobileValid;
    }

    public boolean isEmailValid(boolean setMessage) {
        if (validator.isValidEmail(fields.getEmail())) {
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
        if (validator.isValidPassword(fields.getPassword())) {
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

    public boolean isMobileValid(boolean setMessage) {
        if (validator.isValidMobile(fields.getMobile())) {
            errors.setMobile(null);
            notifyPropertyChanged(BR.mobileError);
            return true;
        } else {
            if (setMessage) {
                errors.setMobile("Mobile number is not valid");
                notifyPropertyChanged(BR.mobileError);
            }
            return false;
        }
    }

    public void onClick() {
        if (isEmpty()) {
            signupStatus.setSuccess(false);
            signupStatus.setId(0);
            signupStatus.setMessage("Please complete the form");
        } else if (!isValid(true)) {
            signupStatus.setSuccess(false);
            signupStatus.setId(0);
            signupStatus.setMessage(null);
        } else {
            signupStatus.setSuccess(true);
            signupStatus.setId(100);
            signupStatus.setMessage(null);
        }
        buttonClick.setValue(signupStatus);
    }

    public MutableLiveData<SignupStatus> getSignupStatus() {
        return buttonClick;
    }

    public SignupFields getFields() {
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

    @Bindable
    public String getMobileError() {
        return errors.getMobile();
    }

    @Bindable
    public String getUserTypeError() {
        return errors.getUserType();
    }
}
