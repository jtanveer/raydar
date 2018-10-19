package com.jtanveer.raydar.ui.login.model;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.jtanveer.raydar.BR;
import com.jtanveer.raydar.validation.InputValidator;
import com.jtanveer.raydar.validation.ValidationStatus;

public class LoginForm extends BaseObservable {

    private LoginFields fields = new LoginFields();
    private LoginErrorFields errors = new LoginErrorFields();
    private ValidationStatus validationStatus = new ValidationStatus();
    private MutableLiveData<ValidationStatus> validation = new MutableLiveData<>();
    private InputValidator validator = new InputValidator();

    public boolean isEmpty() {
        boolean emptyEmail = isEmptyField(fields.getEmail());
        boolean emptyPassword = isEmptyField(fields.getPassword());
        return  emptyEmail || emptyPassword;
    }

    private boolean isEmptyField(String field) {
        return field == null || TextUtils.isEmpty(field);
    }

    public boolean isValid(boolean setMessage) {
        boolean emailValid = isEmailValid(setMessage);
        boolean passwordValid = isPasswordValid(setMessage);
        return emailValid && passwordValid;
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

    public void onClick() {
        if (isEmpty()) {
            validationStatus.setSuccess(false);
            validationStatus.setMessage("Please complete the form");
        } else if (!isValid(true)) {
            validationStatus.setSuccess(false);
            validationStatus.setMessage(null);
        } else {
            validationStatus.setSuccess(true);
            validationStatus.setMessage(null);
        }
        validation.setValue(validationStatus);
    }

    public MutableLiveData<ValidationStatus> getValidationStatus() {
        return validation;
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
