package com.jtanveer.raydar.form;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.jtanveer.raydar.BR;
import com.jtanveer.raydar.lifecycle.SingleLiveEvent;
import com.jtanveer.raydar.validation.InputValidator;
import com.jtanveer.raydar.validation.ValidationStatus;
import com.jtanveer.raydar.validation.field.UserErrorFields;
import com.jtanveer.raydar.validation.field.UserFields;

public class LoginForm extends BaseObservable {

    private UserFields fields = new UserFields();
    private UserErrorFields errors = new UserErrorFields();
    private ValidationStatus validationStatus = new ValidationStatus();
    private SingleLiveEvent<ValidationStatus> validation = new SingleLiveEvent<>();
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

    public SingleLiveEvent<ValidationStatus> getValidationStatus() {
        return validation;
    }

    public UserFields getFields() {
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
