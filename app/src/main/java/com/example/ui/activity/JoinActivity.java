package com.example.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.ui.AppHelper;
import com.example.ui.R;
import com.example.ui.UserAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinActivity extends AppCompatActivity {
    // 1. DB 읽거나 쓰기 위해서 DatabaseReference 인스턴스 필요.
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private EditText inputID, inputPw, inputRePw, inputName, inputNum, inputBr, inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("UI");

        Button confirm_button = findViewById(R.id.confirm_button);
        inputID = findViewById(R.id.inputID);
        inputPw = findViewById(R.id.inputPw);
        inputRePw = findViewById(R.id.inputRePw);
        inputName = findViewById(R.id.inputName);
        inputNum = findViewById(R.id.inputNum);
        inputBr = findViewById(R.id.inputBr);
        inputEmail = findViewById(R.id.inputEmail);

        confirm_button.setOnClickListener(v -> {
            String strID = inputID.getText().toString();
            String strPw = inputPw.getText().toString();
            String strName = inputName.getText().toString();
            String strNum = inputNum.getText().toString();
            String strBr = inputBr.getText().toString();
            String strEmail = inputEmail.getText().toString();

            // Firebase Auth 진행
            mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPw).addOnCompleteListener(JoinActivity.this, task -> {
                if (task.isSuccessful()) {
                    // 가입 성공
                    FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                    UserAccount account = new UserAccount();
                    account.setIdToken(firebaseUser.getUid());
                    account.setUserEmail(firebaseUser.getEmail());
                    account.setPassword(strPw);
                    account.setPhoneNum(strNum);
                    account.setUserID(strID);
                    account.setUserName(strName);
                    Log.i("Inner", "7");
                    account.setUserBr(strBr);
                    Log.i("Inner", "8");

                    mDatabase.child("users").child(firebaseUser.getUid()).setValue(account);
                    Toast.makeText(JoinActivity.this, "회원가입에 성공하였습니다.", Toast.LENGTH_LONG).show();

                    System.exit(0);
                } else
                    Toast.makeText(JoinActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            });
        });
    }
}