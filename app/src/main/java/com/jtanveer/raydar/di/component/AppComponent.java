package com.jtanveer.raydar.di.component;

import android.app.Application;

import com.jtanveer.raydar.App;
import com.jtanveer.raydar.di.module.ActivityModule;
import com.jtanveer.raydar.di.module.AppModule;
import com.jtanveer.raydar.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules={ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
