package com.jtanveer.raydar.ui.root;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtanveer.raydar.R;
import com.jtanveer.raydar.databinding.FragmentSignupBinding;
import com.jtanveer.raydar.ui.home.HomeActivity;
import com.jtanveer.raydar.viewmodel.SignupViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SignupFragment extends Fragment {

    private FragmentSignupBinding binding;

    private SignupViewModel mViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureDagger();
        setupViewModel(savedInstanceState);
    }

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void setupViewModel(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SignupViewModel.class);
        if (savedInstanceState == null) {
            mViewModel.init();
        }
        binding.setModel(mViewModel);
        setupButtonClick();
    }

    private void setupButtonClick() {
        mViewModel.getValidationStatus().observe(this, validationStatus -> {
            if (validationStatus.isSuccess()) {
                performSignup();
            } else {
                if (validationStatus.getMessage() != null) {
                    Snackbar.make(binding.btSignup, validationStatus.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void performSignup() {
        mViewModel.getSignupStatus().observe(this, id -> {
            if (id > 0) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                getActivity().finish();
            } else {
                Snackbar.make(binding.btSignup, "User already exists", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
