package com.example.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ui.R;
import com.example.ui.SHA256;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuth.signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 배경 Gif 처리 위한 코드.
        ImageView imageView = findViewById(R.id.imageview);
        Glide.with(this).load(R.raw.lala2).into(imageView);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText emailText = findViewById(R.id.userEmail);
        EditText passwordText = findViewById(R.id.userPw);
        Button login = findViewById(R.id.button2);
        Button join = findViewById(R.id.join);

        login.setOnClickListener(v -> {
            String email = emailText.getText().toString().trim();

            String password;
            SHA256 sha256 = new SHA256();
            try {
                password = sha256.encrypt(sha256.encrypt(passwordText.getText().toString().trim()));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if (email.equals("") || password.equals(""))
                Toast.makeText(this, "Please enter email, password.", Toast.LENGTH_SHORT).show();
            else
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Intent intent = new Intent(getApplicationContext(), MainViewActivity.class);
                        intent.putExtra("userToken",mAuth.getCurrentUser().getUid());
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
        });

        join.setOnClickListener(v -> {
            Intent intent = new Intent(this, JoinActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    private void updateUI(FirebaseUser user) {
        Log.i(TAG, String.valueOf(user));
    }

    private void reload() {

    }
}