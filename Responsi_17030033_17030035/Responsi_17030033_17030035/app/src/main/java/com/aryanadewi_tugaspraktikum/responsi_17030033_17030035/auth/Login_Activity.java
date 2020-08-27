package com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private CheckBox checkBox;
    private android.R.attr R;

    public Login_Activity(android.R.attr r) {
        R = r;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.attr.layout.activity_login1);

        edtEmail = findViewById(android.R.attr.id.simple_login_email);
        edtPassword = findViewById(android.R.attr.id.simple_login_password);
        checkBox = findViewById(android.R.attr.id.checkBoxRememberMe);
        Button btnSignIn = findViewById(android.R.attr.id.email_sign_in_button);

        edtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == android.R.attr.id.simple_login_password || actionId == EditorInfo.IME_NULL){
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        if (!new User(this).isUserLogOut()){
            startHomeActivity();
        }
    }


    private void attemptLogin() {
        // Reset errors.
        edtEmail.setError(null);
        edtPassword.setError(null);
        // Store values at the time of the login attempt.
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            edtPassword.setError("Email dan Password Salah!");
            focusView = edtPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Inputkan Email Anda Kembali");
            focusView = edtEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            edtEmail.setError("Email dan Password Salah!");
            focusView = edtEmail;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // save data in local shared preferences
            if (checkBox.isChecked())
                saveLoginDetails(email, password);
            startHomeActivity();
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(String.valueOf(this));
        startActivity(intent);
        finish();
    }

    private void saveLoginDetails(String email, String password) {
        new User(this).saveLoginDetails(email, password);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.equals("aryanalutan@gmail.com");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4 && password.equals("123456");
    }
}
