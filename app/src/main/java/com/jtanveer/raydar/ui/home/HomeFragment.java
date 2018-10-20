package com.jtanveer.raydar.ui.home;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jtanveer.raydar.BR;
import com.jtanveer.raydar.R;
import com.jtanveer.raydar.databinding.FragmentHomeBinding;
import com.jtanveer.raydar.ui.root.MainActivity;
import com.jtanveer.raydar.viewmodel.HomeViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dmax.dialog.SpotsDialog;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private static final int UPDATE_MOBILE = 86;

    private FragmentHomeBinding binding;

    private HomeViewModel mViewModel;

    private AlertDialog dialog;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public static HomeFragment newInstance(Long id) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupDialog();
        configureDagger();
        setupViewModel(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("showDialog", dialog.isShowing());
    }

    private void setupDialog() {
        dialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .setCancelable(false)
                .setTheme(R.style.Dialog_Logout)
                .build();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void setupViewModel(Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        if (savedInstanceState == null) {
            Long id = getArguments().getLong("id");
            mViewModel.init(id);
        } else {
            if (savedInstanceState.getBoolean("showDialog")) {
                if (dialog != null) {
                    dialog.show();
                }
            }
        }
        binding.setModel(mViewModel);
        setupUser();
        setupButtonClick();
    }

    private void setupUser() {
        mViewModel.getUser().observe(this, user -> binding.setVariable(BR.user, user));
    }

    private void setupButtonClick() {
        mViewModel.getEditButtonClickStatus().observe(this, this::prepareAlertDialog);
        mViewModel.getUserTypeClickStatus().observe(this, userType -> Toast.makeText(getContext(),
                String.format("Your account type is %s", userType), Toast.LENGTH_LONG).show());
        mViewModel.getLogoutStatus().observe(this, logout -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        binding.btLogout.setOnClickListener(view -> {
            if (dialog != null) {
                dialog.show();
            }
            mViewModel.onLogoutButtonClick();
        });
    }

    private void prepareAlertDialog(String mobile) {
        FragmentManager fm = getFragmentManager();
        EditMobileDialogFragment alertDialog = EditMobileDialogFragment.newInstance(getArguments().getLong("id"), mobile);
        alertDialog.setTargetFragment(this, UPDATE_MOBILE);
        alertDialog.show(fm, "Edit Number");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPDATE_MOBILE && resultCode == RESULT_OK) {
            String mobile = data.getStringExtra("mobile");
            mViewModel.getMobileUpdateStatus(getArguments().getLong("id"), mobile).observe(this, success -> {
                if (success) {
                    binding.tvMobile.setText(mobile);
                    Toast.makeText(getContext(), "Successfully updated", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
