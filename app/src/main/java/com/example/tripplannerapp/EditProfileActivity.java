package com.example.tripplannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    ImageButton btn_backToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btn_backToProfile = findViewById(R.id.btn_backToProfile);

        btn_backToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToProfile();
            }
        });

    }

    public void backToProfile(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
