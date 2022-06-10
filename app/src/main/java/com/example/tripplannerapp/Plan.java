package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Plan extends AppCompatActivity {

    FloatingActionButton addPlanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        addPlanBtn = findViewById(R.id.addActivityBtn);

        String tname = getIntent().getStringExtra("Trip name");
        String sdate = getIntent().getStringExtra("Start Date");
        String edate = getIntent().getStringExtra("End Date");

        TextView showTripNameTxt = findViewById(R.id.showTripName);
        TextView showStartDateTxt = findViewById(R.id.tv_startDate);
        TextView showEndDateTxt = findViewById(R.id.tv_activityType);

        showTripNameTxt.setText(tname);
        showStartDateTxt.setText(sdate);
        showEndDateTxt.setText(edate);

        addPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Plan.this,AddPlan.class);
                startActivity(intent);
            }
        });
    }
}