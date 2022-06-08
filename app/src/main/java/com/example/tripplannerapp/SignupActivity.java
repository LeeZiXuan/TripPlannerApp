package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    EditText username,email, password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username=findViewById(R.id.username);
        email=findViewById(R.id.emailID);
        password=findViewById(R.id.password);
        signUp=findViewById(R.id.signupBtn);

        TextView hasAccBtn=findViewById(R.id.hasAccount);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        hasAccBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }
}