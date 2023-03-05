package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    Button btn_signup,btn_loginPage;
    TextInputEditText signup_email,signup_phone,signup_name,signup_password;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db=FirebaseFirestore.getInstance();
        signup_email=findViewById(R.id.signup_email);
        signup_phone=findViewById(R.id.signup_phone);
        signup_name=findViewById(R.id.signup_name);
        signup_password=findViewById(R.id.signup_password);
        btn_signup=findViewById(R.id.signup_btn);
        btn_loginPage=findViewById(R.id.signup_login_page);
        btn_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Signup.this,login_page.class);
                startActivity(intent);
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> user = new HashMap<>();
                user.put("email", signup_email.getText().toString());
                user.put("name", signup_name.getText().toString());

                user.put("phone", signup_phone.getText().toString());
                user.put("password", signup_password.getText().toString());




                db.collection("students").document(signup_email.getText().toString()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Signup.this, "USER REGISTERED", Toast.LENGTH_SHORT).show();
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}