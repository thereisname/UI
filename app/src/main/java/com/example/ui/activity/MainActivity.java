package com.example.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ui.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userToken = getIntent().getStringExtra("userToken");

        ImageView myPage = findViewById(R.id.myPage);
        ImageView home = findViewById(R.id.home);

        mDatabase = FirebaseDatabase.getInstance().getReference("UI");


        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainViewActivity.class);
            intent.putExtra("userToken", userToken);
            startActivity(intent);
            finish();
        });

        myPage.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyPageActivity.class);
            intent.putExtra("userToken", userToken);
            startActivity(intent);
            finish();
        });
    }
}