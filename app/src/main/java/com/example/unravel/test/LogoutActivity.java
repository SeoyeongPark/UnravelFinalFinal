package com.example.unravel.test;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutActivity extends AppCompatActivity {

    private Button mLogOutBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_logout);
        setContentView(R.layout.fragment_settings);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null) {

                    startActivity(new Intent(LogoutActivity.this, LoginActivity.class));
                }
            }
        };

        mLogOutBtn = (Button) findViewById(R.id.logOutBtn);
        Log.d("MAuth","Inside Logout Act.");

        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                mLogOutBtn.setText("Logged Out");
             }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MAuth","Start Logout");

        mAuth.addAuthStateListener(mAuthListener);
    }
}
