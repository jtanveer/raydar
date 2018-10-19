package com.jtanveer.raydar.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.jtanveer.raydar.di.key.ViewModelKey;
import com.jtanveer.raydar.viewmodel.LoginViewModel;
import com.jtanveer.raydar.viewmodel.SignupViewModel;
import com.jtanveer.raydar.viewmodel.FactoryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel repoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel.class)
    abstract ViewModel bindSignupViewModel(SignupViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
