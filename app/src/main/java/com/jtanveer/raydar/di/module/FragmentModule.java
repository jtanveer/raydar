package com.jtanveer.raydar.di.module;

import com.jtanveer.raydar.ui.home.EditMobileDialogFragment;
import com.jtanveer.raydar.ui.home.HomeFragment;
import com.jtanveer.raydar.ui.root.LoginFragment;
import com.jtanveer.raydar.ui.root.SignupFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector
    abstract SignupFragment contributeSignupFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract EditMobileDialogFragment contributeEditMobileFragment();
}
