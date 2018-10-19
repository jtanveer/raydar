package com.jtanveer.raydar.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jtanveer.raydar.database.dao.UserDao;
import com.jtanveer.raydar.database.model.User;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class UserRepository {

    private UserDao userDao;
    private Executor executor;

    @Inject
    public UserRepository(UserDao userDao, Executor executor) {
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(String email) {
        MutableLiveData<User> data = new MutableLiveData<>();
        executor.execute(() -> data.postValue(userDao.get(email)));
        return data;
    }

    public void saveUser(String email, String password, String mobile, String firstName, String lastName, String type) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setType(type);
        executor.execute(() -> userDao.save(user));
    }
}
