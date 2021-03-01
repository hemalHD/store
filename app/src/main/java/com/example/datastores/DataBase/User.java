package com.example.datastores.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String mobile;

    @ColumnInfo
    public String email;

    @ColumnInfo
    public String address;

    public User(String name, String mobile, String email, String address) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }
}