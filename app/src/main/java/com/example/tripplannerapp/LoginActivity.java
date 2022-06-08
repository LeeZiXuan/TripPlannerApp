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

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button btn_login;
    DataBaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.emailID1);
        password=findViewById(R.id.password1);
        //btn_login = (Button) findViewById(R.id.loginBtn);
        btn_login = findViewById(R.id.loginBtn);
        DB=new DataBaseHelper(this);

        TextView hasResBtn=findViewById(R.id.hasRegister);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailID=email.getText().toString();
                String pass=password.getText().toString();

                if(TextUtils.isEmpty(emailID) || TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this, "All fields Required",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkPass = DB.checkPassword(emailID,pass);
                    if(checkPass==true){
                        Toast.makeText(LoginActivity.this, "Login Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
                //OpenDatabase
                //DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);

                //GoMainPage
                //openHomePage();

            }
        });

        hasResBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public void openHomePage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}