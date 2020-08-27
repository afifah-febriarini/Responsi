package com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.auth_firebase;

import android.content.Intent;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.tools.build.bundletool.device.Device;
import com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import shadow.bundletool.com.android.ddmlib.IDevice;

public class Login_Firebase_Activity<GoogleSignInClient, GoogleSignInAccount, Task> extends AppCompatActivity {
    Integer RC_SIGN_IN = 1;
    GoogleSignInClient googleSignInClient;
    GoogleSignInOptions googleSignInOptions;

    FirebaseAuth firebaseAuth;

    SignInButton signInButton;
    private PasspointConfiguration GoogleAuthProvider;
    private Device GoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginfirebase);

        configurationFirebaseSignInGoogle();
        signInButton = findViewById(R.id.btn_google_sign_in);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object intent = googleSignInClient.getClass();
                startActivityForResult((Intent) intent, RC_SIGN_IN);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) goToMainActivity();
    }

    private void configurationFirebaseSignInGoogle() {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = (GoogleSignInClient) GoogleSignIn.getClass(this, googleSignInOptions);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IDevice.DeviceState task = GoogleSignIn.getState(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Google Sign In Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) throws InterruptedException {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.wait(), null);
        final com.google.android.gms.tasks.Task<AuthResult> google_sign_in_failed = firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {

            }

            @Override
            public <loginFirebaseActivity, authResult> void onComplete(@NonNull Task task) {
                if (!task.isSuccessful())
                    Toast.makeText(loginFirebaseActivity.this, "Google Sign In Failed", Toast.LENGTH_LONG).show();
                else goToMainActivity();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(String.valueOf(Login_Firebase_Activity.this));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
