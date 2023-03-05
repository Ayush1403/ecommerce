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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class login_page extends AppCompatActivity {
       Button btn_login,btn_signup;
       TextInputEditText username,password;
       FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_login_page);
        btn_login=findViewById(R.id.login_signin_btn);
        btn_signup= findViewById(R.id.login_signup_btn);
        username=findViewById(R.id.login_username);
        password=findViewById(R.id.login_password);
        db=FirebaseFirestore.getInstance();
       btn_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DocumentReference docRef = db.collection("students").document(username.getText().toString());
               docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                       if (task.isSuccessful()) {
                           DocumentSnapshot document = task.getResult();
                           if (document.exists()) {
                               Map<String,Object> student=document.getData();
                               if (student.get("password").toString().equals(password.getText().toString()))
                               {
//                                   SharedPreferences sp=getSharedPreferences("myquiz",MODE_PRIVATE);
//                                   SharedPreferences.Editor editor=sp.edit();
//                                   editor.putString("userid",username.getText().toString());
//                                   editor.commit();
                                   Intent intent=new Intent(login_page.this,MainScreen.class);
                                   startActivity(intent);
                               }

                           } else {

                               Toast.makeText(login_page.this, "invalid user", Toast.LENGTH_SHORT).show();
                           }
                       } else {
                           Toast.makeText(login_page.this, "connectivity issue", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
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

    }
