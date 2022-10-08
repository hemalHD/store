package com.example.datastores;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.datastores.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static String USERNAME="hemal";
    public static String PASSWORD="password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (!pref.getString(USERNAME,"").isEmpty()&&!pref.getString(PASSWORD,"").isEmpty()){
            binding.edUserName.setText(pref.getString(USERNAME,""));
            binding.edPassword.setText(pref.getString(PASSWORD,""));
        }
        binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edUserName.getText().toString().isEmpty()||binding.edPassword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Proper Data", Toast.LENGTH_SHORT).show();
                }else {
                    editor.putString(USERNAME, binding.edUserName.getText().toString());
                    editor.putString(PASSWORD, binding.edPassword.getText().toString());
                }
            }
        });
    }
}