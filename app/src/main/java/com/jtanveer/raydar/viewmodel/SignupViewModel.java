package com.jtanveer.raydar.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.jtanveer.raydar.lifecycle.SingleLiveEvent;
import com.jtanveer.raydar.repository.UserRepository;
import com.jtanveer.raydar.form.SignupForm;
import com.jtanveer.raydar.validation.ValidationStatus;
import com.jtanveer.raydar.validation.field.UserFields;

import javax.inject.Inject;

public class SignupViewModel extends ViewModel {
    private SignupForm signup;

    @Inject
    UserRepository userRepository;

    @Inject
    public SignupViewModel() {
    }

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

    public void onUserTypeSelected(View view) {
        if (view != null) {
            signup.getFields().setUserType(((TextView) view).getText().toString());
        }
    }

    public SingleLiveEvent<ValidationStatus> getValidationStatus() {
        return signup.getValidationStatus();
    }

    public LiveData<Long> getSignupStatus() {
        UserFields fields = signup.getFields();
        return userRepository.signup(fields.getEmail(), fields.getPassword(), fields.getMobile(),
                fields.getFirstName(), fields.getLastName(), fields.getUserType());
    }

    @BindingAdapter("error")
    public static void setError(Spinner spinner, String errorMessage) {
        TextView tvSelected = (TextView) spinner.getSelectedView();
        if (tvSelected != null) {
            tvSelected.setError(errorMessage);
        }
    }
}
