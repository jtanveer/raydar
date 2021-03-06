package com.jtanveer.raydar.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jtanveer.raydar.database.model.User;
import com.jtanveer.raydar.form.HomeForm;
import com.jtanveer.raydar.lifecycle.SingleLiveEvent;
import com.jtanveer.raydar.repository.UserRepository;

import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private HomeForm home;
    private LiveData<User> user;

    @Inject
    UserRepository userRepository;

    @Inject
    ScheduledExecutorService executor;

    @Inject
    public HomeViewModel() {
    }

    public void init(Long id) {
        home = new HomeForm();
        if (user == null) {
            user = userRepository.getUser(id);
        }
    }

    public HomeForm getHome() {
        return home;
    }

    public void onEditButtonClick() {
        User userModel = user.getValue();
        if (userModel != null) {
            home.onEditButtonClick(userModel.getMobile());
        }
    }

    public void onTypeButtonClick() {
        User userModel = user.getValue();
        if (userModel != null) {
            home.onTypeButtonClick(userModel.getType());
        }
    }

    public void onLogoutButtonClick() {
        home.onLogoutButtonClick(executor);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public SingleLiveEvent<String> getEditButtonClickStatus() {
        return home.getEditButtonClickStatus();
    }

    public SingleLiveEvent<String> getUserTypeClickStatus() {
        return home.getTypeButtonClickStatus();
    }

    public SingleLiveEvent<Boolean> getLogoutStatus() {
        return home.getLogoutButtonClickStatus();
    }

    public LiveData<Boolean> getMobileUpdateStatus(Long id, String mobile) {
        return userRepository.updateMobile(id, mobile);
    }
}
