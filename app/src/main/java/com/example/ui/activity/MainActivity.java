package com.example.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.ui.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView myPage = findViewById(R.id.myPage);
        ImageView home = findViewById(R.id.home);

        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainViewActivity.class);
            startActivity(intent);
            finish();
        });

        myPage.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyPageActivity.class);
            startActivity(intent);
            finish();
        });
    }
}