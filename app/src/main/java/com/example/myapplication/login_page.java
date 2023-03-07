package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class login_page extends AppCompatActivity {
       Button btn_login,btn_signup,forget_btn;
       TextInputEditText username,password;
       FirebaseFirestore db;
       FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_login_page);
        btn_login=findViewById(R.id.login_signin_btn);
        btn_signup= findViewById(R.id.login_signup_btn);
        username=findViewById(R.id.login_username);
        password=findViewById(R.id.login_password);
        forget_btn=findViewById(R.id.login_forgot_btn);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        forget_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_page.this,forgotpassword.class);
                startActivity(intent);
            }
        });
       btn_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               siginusingemail();

           }
       });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(login_page.this,Signup.class);
                startActivity(intent);
            }
        });
    }
    private void siginusingemail(){
        auth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    if(auth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(login_page.this,MainScreen.class));
                    }
                    else {
                        Toast.makeText(login_page.this, "Please Verify your email", Toast.LENGTH_SHORT).show();
                    }
                }
               else {
                    Toast.makeText(login_page.this, "Invalid Login credentials", Toast.LENGTH_SHORT).show();
                }
            }
  });
    }
    }
