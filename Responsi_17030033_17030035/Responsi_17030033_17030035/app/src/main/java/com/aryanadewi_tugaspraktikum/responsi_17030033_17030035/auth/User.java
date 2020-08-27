package com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.auth;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class User extends AppCompatActivity {
    Context context;

    User(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String email, String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.apply();
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }

    public boolean isUserLogOut(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = Objects.requireNonNull(sharedPreferences.getString("Email", "")).isEmpty();
        boolean isPasswordEmpty = Objects.requireNonNull(sharedPreferences.getString("Password", "")).isEmpty();
        return isEmailEmpty || isPasswordEmpty;

    }
}
