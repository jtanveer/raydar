package com.jtanveer.raydar.ui.signup;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.jtanveer.raydar.ui.signup.model.SignupForm;
import com.jtanveer.raydar.ui.signup.model.SignupStatus;

import javax.inject.Inject;

public class SignupViewModel extends ViewModel {
    private SignupForm signup;

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

    public MutableLiveData<SignupStatus> getSignupStatus() {
        return signup.getSignupStatus();
    }

    @BindingAdapter("error")
    public static void setError(Spinner spinner, String errorMessage) {
        TextView tvSelected = (TextView) spinner.getSelectedView();
        if (tvSelected != null) {
            tvSelected.setError(errorMessage);
        }
    }
}
