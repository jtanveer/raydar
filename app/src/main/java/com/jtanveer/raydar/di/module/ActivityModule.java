package com.jtanveer.raydar.di.module;

import com.jtanveer.raydar.ui.SplashActivity;
import com.jtanveer.raydar.ui.home.HomeActivity;
import com.jtanveer.raydar.ui.root.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract HomeActivity contributeDeliveryDetailActivity();
}
