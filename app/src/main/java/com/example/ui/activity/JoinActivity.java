package com.example.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.android.volley.toolbox.Volley;
import com.example.ui.AppHelper;
import com.example.ui.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinActivity extends AppCompatActivity {
    // 1. DB 읽거나 쓰기 위해서 DatabaseReference 인스턴스 필요.
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button confirm_button = findViewById(R.id.confirm_button);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        confirm_button.setOnClickListener(v -> {
            // Write a message to the database
            writeNewUser("test", "name", "email@example.com");
        });
    }

    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email, userId);

        mDatabase.child("users").child(userId).setValue(user);
    }
}

class User {
    public String username;
    public String email;
    public String ID;

    public User() {

    }
    public User(String username, String email, String ID) {
        this.username = username;
        this.email = email;
        this.ID = ID;
    }
}