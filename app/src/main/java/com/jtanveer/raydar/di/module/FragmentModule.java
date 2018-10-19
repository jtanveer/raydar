package com.jtanveer.raydar.di.module;

import com.jtanveer.raydar.ui.login.LoginFragment;
import com.jtanveer.raydar.ui.signup.SignupFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector
    abstract SignupFragment contributeSignupFragment();
}
