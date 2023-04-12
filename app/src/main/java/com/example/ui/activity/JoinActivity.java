package com.example.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.toolbox.Volley;
import com.example.ui.AppHelper;
import com.example.ui.R;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);



        if (AppHelper.requestQueue == null)
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());

    }
}