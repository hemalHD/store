package com.example.datastores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Notification;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;

import com.example.datastores.DataBase.MyDataBaseApplication;
import com.example.datastores.DataBase.User;
import com.example.datastores.DataBase.UserDao;
import com.example.datastores.DataBase.UserRoomDataBase;
import com.example.datastores.databinding.ActivityDataStoreBinding;
import com.example.datastores.databinding.DailogueAddUserBinding;
import com.example.datastores.databinding.ItemUserBinding;

import java.util.ArrayList;

public class DataStoreActivity extends AppCompatActivity {
    ActivityDataStoreBinding binding;
    UserDao userDao;
    GenAdapter<User, ItemUserBinding> adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        final UserDao userDao = ((MyDataBaseApplication) getApplicationContext()).getMyDatabase().getUserDao();

        userDao = ((MyDataBaseApplication) getApplicationContext()).getMyDatabase().getUserDao();
        AlertDialog.Builder builder = new AlertDialog.Builder(DataStoreActivity.this);
//                View dialogLayout = inflater.inflate(R.layout.dailogue_stylists, null);
//                dialogLayout.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                builder.setPositiveButton("OK", null);
//                builder.setView(dialogLayout);
        DailogueAddUserBinding dialogLayoutBinding = DailogueAddUserBinding
                .inflate(LayoutInflater.from(DataStoreActivity.this));
        builder.setView(dialogLayoutBinding.getRoot());
        builder.create();
        AlertDialog dialog = builder.show();
        dialogLayoutBinding.btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        userDao.insertUser(new User(dialogLayoutBinding.edName.getText().toString(), dialogLayoutBinding.edNumber.getText().toString(), dialogLayoutBinding.edEmail.getText().toString(), dialogLayoutBinding.edAddress.getText().toString()));
                        loadData();
                    }
                });
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loadData();
    }

    void loadData() {

        adapter1 = new GenAdapter<User, ItemUserBinding>(DataStoreActivity.this, userDao.getAll()) {
            @Override
            public int getLayoutResId() {
                return R.layout.item_user;
            }

            @Override
            public void onBindData(User sliderItem, int position, ItemUserBinding dataBinding) {
//
                dataBinding.tvName.setText(sliderItem.name);
//                dataBinding.txtSubject.setText(sliderItem.getSubject());
//                dataBinding.txtMsg.setText(Html.fromHtml(sliderItem.getMessage()));

            }

            @Override
            public void onItemClick(User sliderItem, int position) {
//                            Toast.makeText(getContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public ArrayList<User> performFilter(String searchText, ArrayList<User> originalList) {
                return null;
            }


        };
        binding.rvUsers.setLayoutManager(new GridLayoutManager(DataStoreActivity.this, 1, RecyclerView.VERTICAL, false));
        binding.rvUsers.setAdapter(adapter1);
    }
}