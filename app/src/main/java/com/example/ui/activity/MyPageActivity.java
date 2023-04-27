package com.example.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ui.fragment.FragmentBoard;
import com.example.ui.fragment.FragmentBookmark;
import com.example.ui.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MyPageActivity extends AppCompatActivity {
    FragmentBoard fragmentBoard;
    FragmentBookmark fragmentBookmark;
    TextView nickName;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        fragmentBoard = new FragmentBoard();
        fragmentBookmark = new FragmentBookmark();

        mDatabase = FirebaseDatabase.getInstance().getReference("UI");
        nickName = findViewById(R.id.nickName);

        String userToken = getIntent().getStringExtra("userToken");

        mDatabase.child("users").child(userToken).child("userNickName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nickName.setText(String.valueOf(snapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference("UI");


        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentBoard).commit();

        ImageView home = findViewById(R.id.home);
        ImageView upload = findViewById(R.id.upload);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("게시물"));
        tabs.addTab(tabs.newTab().setText("북마크"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("position", String.valueOf(position));
                Fragment selected = null;
                if (position == 0)
                    selected = fragmentBoard;
                else
                    selected = fragmentBookmark;

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        home.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainViewActivity.class);
            intent.putExtra("userToken", userToken);
            startActivity(intent);
            finish();
        });

        upload.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userToken", userToken);
            startActivity(intent);
            finish();
        });
    }
}