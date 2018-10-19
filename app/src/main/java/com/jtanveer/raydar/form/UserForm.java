package com.jtanveer.raydar.form;

import android.databinding.BaseObservable;

import com.jtanveer.raydar.lifecycle.SingleLiveEvent;
import com.jtanveer.raydar.validation.InputValidator;
import com.jtanveer.raydar.validation.ValidationStatus;
import com.jtanveer.raydar.validation.field.UserFields;

public class UserForm extends BaseObservable {

    private UserFields fields = new UserFields();
    private ValidationStatus validationStatus = new ValidationStatus();
    private SingleLiveEvent<ValidationStatus> validation = new SingleLiveEvent<>();
    private InputValidator validator = new InputValidator();

    public boolean isMobileValid() {
        return validator.isValidMobile(fields.getMobile());
    }

    public void onClick() {
        validationStatus.setSuccess(isMobileValid());
        validationStatus.setMessage(null);
        validation.setValue(validationStatus);
    }

    public SingleLiveEvent<ValidationStatus> getValidationStatus() {
        return validation;
    }
}
