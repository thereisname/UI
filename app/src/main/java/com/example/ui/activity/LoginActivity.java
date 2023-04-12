package com.example.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ui.AppHelper;
import com.example.ui.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        EditText emailText = findViewById(R.id.userEmail);
        EditText passwordText = findViewById(R.id.userPw);

        if (AppHelper.requestQueue == null)
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 배경 Gif 처리 위한 코드.
        ImageView imageView = findViewById(R.id.imageview);
        Glide.with(this).load(R.raw.lala2).into(imageView);

        Button login = findViewById(R.id.button2);
        Button join = findViewById(R.id.join);

//        login.setOnClickListener(v -> selectRequest());
        login.setOnClickListener(v -> {
            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();
            if (email.equals("") || password.equals(""))
                Toast.makeText(this, "email, password를 입력하세요.", Toast.LENGTH_SHORT).show();
            else
                singIn(email, password);
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

    private void singIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            startActivity(new Intent(getApplicationContext(), MainViewActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Log.i(TAG, String.valueOf(user));
    }

    private void reload() {

    }
}