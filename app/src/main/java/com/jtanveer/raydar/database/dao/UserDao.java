package com.jtanveer.raydar.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.jtanveer.raydar.database.model.User;

import static android.arch.persistence.room.OnConflictStrategy.ABORT;

@Dao
public interface UserDao {

    @Insert(onConflict = ABORT)
    long save(User user);

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    User get(long id);

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    User get(String email);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    User get(String email, String password);
}
