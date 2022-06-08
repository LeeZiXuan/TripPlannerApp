package com.example.tripplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        Button btn_login = findViewById(R.id.loginBtn);
        TextView signupNow = findViewById(R.id.signupHere);

        fAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailTxt = email.getText().toString();
                String passTxt = password.getText().toString();

                if(TextUtils.isEmpty(emailTxt)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(passTxt)){
                    password.setError("Password is required");
                    return;
                }

                //authenticate the user
                fAuth.signInWithEmailAndPassword(emailTxt,passTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                //GoMainPage
                //openHomePage();

            }
        });

        signupNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

    }

    // check if user is login before
    /*@Override
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() !=null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
    }*/

    /*public void openHomePage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/
}