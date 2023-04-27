package com.example.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.ui.fragment.FragmentClient;
import com.example.ui.fragment.FragmentImage;
import com.example.ui.R;

public class MainViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        String userToken = getIntent().getStringExtra("userToken");

        ImageView myPage = findViewById(R.id.myPage);
        FragmentImage fragmentImage = new FragmentImage();
        FragmentClient fragmentClient = new FragmentClient();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragmentImage).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, fragmentClient).commit();

        myPage.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyPageActivity.class);
            intent.putExtra("userToken", userToken);
            startActivity(intent);
            finish();
        });

        ImageView upload = findViewById(R.id.upload);
        upload.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userToken", userToken);
            startActivity(intent);
            finish();
        });

    }
}