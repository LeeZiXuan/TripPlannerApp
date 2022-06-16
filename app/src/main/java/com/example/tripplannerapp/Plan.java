package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Plan extends AppCompatActivity {

    FloatingActionButton addPlanBtn;
    ImageButton btn_editTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        addPlanBtn = findViewById(R.id.addActivityBtn);
        btn_editTrip = findViewById(R.id.btn_editTrip);

        String tDes = getIntent().getStringExtra("destination");
        String sDate = getIntent().getStringExtra("startDate");
        String eDate = getIntent().getStringExtra("endDate");
        String tName = getIntent().getStringExtra("tripName");
        String tDesc = getIntent().getStringExtra("description");
        String aId = getIntent().getStringExtra("trip_id");

        TextView showTripName = findViewById(R.id.showTripName);
        TextView tv_startDate = findViewById(R.id.tv_startDate);
        TextView tv_EndDate = findViewById(R.id.tv_EndDate);
        TextView showTripDestination = findViewById(R.id.showTripDestination);
        TextView showTripDescription = findViewById(R.id.showTripDescription);

        showTripName.setText(tName);
        tv_startDate.setText(sDate);
        tv_EndDate.setText(eDate);
        showTripDestination.setText(tDes);
        showTripDescription.setText(tDesc);

        addPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Plan.this,AddPlan.class);
                startActivity(intent);
            }
        });

        btn_editTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Plan.this, EditTrip.class);

                intent.putExtra("destination", tDes);
                intent.putExtra("startDate", sDate);
                intent.putExtra("endDate", eDate);
                intent.putExtra("tripName", tName);
                intent.putExtra("description", tDesc);
                intent.putExtra("trip_id", aId);

                startActivity(intent);
            }
        });
    }
}