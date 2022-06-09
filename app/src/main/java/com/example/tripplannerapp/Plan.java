package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Plan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        String tname = getIntent().getStringExtra("Trip name");
        String sdate = getIntent().getStringExtra("Start Date");
        String edate = getIntent().getStringExtra("End Date");

        TextView showTripNameTxt = findViewById(R.id.showTripName);
        TextView showStartDateTxt = findViewById(R.id.showStartDate);
        TextView showEndDateTxt = findViewById(R.id.showEndDate);

        showTripNameTxt.setText(tname);
        showStartDateTxt.setText(sdate);
        showEndDateTxt.setText(edate);
    }
}