package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText username,email, password;
    Button signUp;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username=findViewById(R.id.username);
        email=findViewById(R.id.emailID);
        password=findViewById(R.id.password);
        signUp=findViewById(R.id.signupBtn);
        DB=new DataBaseHelper(this);

        TextView hasAccBtn=findViewById(R.id.hasAccount);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String emailID=email.getText().toString();
                String pass=password.getText().toString();
                String name=username.getText().toString();

                if(TextUtils.isEmpty(emailID) || TextUtils.isEmpty(pass) ||TextUtils.isEmpty(name))
                    Toast.makeText(SignupActivity.this, "All fields Required",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkEmail = DB.checkEmail(emailID);
                    if(checkEmail==false) {
                        Boolean insert = DB.InsertData(emailID, pass, name);
                        if (insert == true) {
                            Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignupActivity.this, "User already exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        hasAccBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}