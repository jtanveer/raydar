package com.jtanveer.raydar.ui.login;

import android.arch.lifecycle.ViewModelProviders;
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
import com.jtanveer.raydar.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel(savedInstanceState);
    }

    private void setupViewModel(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        if (savedInstanceState == null) {
            mViewModel.init();
        }
        binding.setModel(mViewModel);
        setupButtonClick();
    }

    private void setupButtonClick() {
        mViewModel.getLoginStatus().observe(this, loginStatus -> {
            if (loginStatus.isSuccess()) {
                Snackbar.make(binding.btLogin, "Success", Snackbar.LENGTH_LONG).show();
            } else {
                if (loginStatus.getMessage() != null) {
                    Snackbar.make(binding.btLogin, loginStatus.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

}
