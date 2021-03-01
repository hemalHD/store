package com.example.datastores.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User where id LIKE :id")
    public User getById(int id);

    @Query("SELECT * FROM User")
    public ArrayList<User> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertUser(User user);

    @Delete
    public void deleteUser(User user);
}