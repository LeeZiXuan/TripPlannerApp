package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

                TripPlannerAppModel tripPlannerAppModel;

                try {
                    tripPlannerAppModel = new TripPlannerAppModel(-1, username.getText().toString(), email.getText().toString(), password.getText().toString());
                    Toast.makeText(SignupActivity.this, tripPlannerAppModel.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(SignupActivity.this, "Error Register", Toast.LENGTH_SHORT).show();
                    tripPlannerAppModel = new TripPlannerAppModel(-1, "error", "error", "error");


                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(SignupActivity.this);
                boolean success = dataBaseHelper.addOne(tripPlannerAppModel);
                Toast.makeText(SignupActivity.this,"Success= " + success, Toast.LENGTH_SHORT).show();

            }
        });

        hasAccBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }
}