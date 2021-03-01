package com.example.datastores.DataBase;

import android.app.Application;

import androidx.room.Room;

public class MyDataBaseApplication extends Application {

    UserRoomDataBase myDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        // when upgrading versions, kill the original tables by using fallbackToDestructiveMigration()
        myDatabase = Room.databaseBuilder(this, UserRoomDataBase.class, UserRoomDataBase.NAME).fallbackToDestructiveMigration().build();
    }

    public UserRoomDataBase getMyDatabase() {
        return myDatabase;
    }

}