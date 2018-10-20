package com.jtanveer.raydar.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.jtanveer.raydar.database.RaydarDatabase;
import com.jtanveer.raydar.database.dao.UserDao;
import com.jtanveer.raydar.repository.UserRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    // --- DATABASE INJECTION ---

    @Provides
    @Singleton
    RaydarDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                RaydarDatabase.class, "raydar.db")
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(RaydarDatabase database) {
        return database.userDao();
    }

    // --- REPOSITORY INJECTION ---

    @Provides
    ScheduledExecutorService provideScheduledExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    UserRepository providUserRepository(UserDao userDao, Executor executor) {
        return new UserRepository(userDao, executor);
    }
}
