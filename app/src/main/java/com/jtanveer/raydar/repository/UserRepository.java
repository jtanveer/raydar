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

    public LiveData<User> getUser(Long id) {
        MutableLiveData<User> data = new MutableLiveData<>();
        executor.execute(() -> data.postValue(userDao.get(id)));
        return data;
    }

    public LiveData<Long> login(String email, String password) {
        MutableLiveData<Long> data = new MutableLiveData<>();
        executor.execute(() -> {
            User user = userDao.get(email, password);
            if (user != null) {
                data.postValue(user.getId());
            } else {
                data.postValue(-1L);
            }
        });
        return data;
    }

    public LiveData<Long> signup(String email, String password, String mobile, String firstName, String lastName, String type) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setType(type);
        MutableLiveData<Long> data = new MutableLiveData<>();
        executor.execute(() -> {
            if (userDao.get(email) == null) {
                data.postValue(userDao.save(user));
            } else {
                data.postValue(-1L);
            }
        });
        return data;
    }

    public LiveData<Boolean> updateMobile(Long id, String mobile) {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        executor.execute(() -> data.postValue(userDao.update(id, mobile) > 0));
        return data;
    }
}
