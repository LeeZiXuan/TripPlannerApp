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

import com.example.tripplannerapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        /*if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/

        // Signup section
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
        EditText username=findViewById(R.id.username);
        EditText phoneNum=findViewById(R.id.phoneNum);
        Button signUp=findViewById(R.id.signupBtn);
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String emailTxt = email.getText().toString().trim();
                String passTxt = password.getText().toString().trim();
                String usernameTxt = username.getText().toString();
                String phoneTxt = phoneNum.getText().toString();

                if(TextUtils.isEmpty(emailTxt)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(passTxt)){
                    password.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(usernameTxt)){
                    username.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(phoneTxt)){
                    phoneNum.setError("Phone number is required");
                    return;
                }

                //register the user in the firebase
                fAuth.createUserWithEmailAndPassword(emailTxt,passTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Users users = new Users(emailTxt, passTxt, usernameTxt, phoneTxt);
                            String id = task.getResult().getUser().getUid();
                            db.getReference().child("Users").child(id).setValue(users);

                            Toast.makeText(SignupActivity.this,"User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(SignupActivity.this,"Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        // If already have account
        TextView hasAcc=findViewById(R.id.loginHere);
        hasAcc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }
}