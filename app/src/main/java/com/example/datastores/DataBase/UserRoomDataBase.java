package com.example.datastores.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserRoomDataBase extends RoomDatabase {
    public static final String NAME = "MyDataBase";
    public abstract UserDao getUserDao();

    private static UserRoomDataBase noteDB;

    public static UserRoomDataBase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }
    private static UserRoomDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                UserRoomDataBase.class,
                NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }
}