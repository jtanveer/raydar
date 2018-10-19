package com.jtanveer.raydar.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jtanveer.raydar.database.dao.UserDao;
import com.jtanveer.raydar.database.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RaydarDatabase extends RoomDatabase {

    public static volatile RaydarDatabase INSTANCE;
    public abstract UserDao userDao();
}
