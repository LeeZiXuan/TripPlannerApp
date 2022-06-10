package com.example.tripplannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityDetailsActivity extends AppCompatActivity {

    FloatingActionButton addActivityBtn;
    ImageButton btn_editAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_details);

        addActivityBtn = findViewById(R.id.addActivityBtn);
        btn_editAct = findViewById(R.id.btn_editAct);

        String aName = getIntent().getStringExtra("act_name");
        String aType = getIntent().getStringExtra("act_type");
        String aStartDate = getIntent().getStringExtra("act_startDateTime");
        String aEndDate = getIntent().getStringExtra("act_endDateTime");
        String aAddress = getIntent().getStringExtra("act_address");
        String aDes = getIntent().getStringExtra("act_des");
        String aId = getIntent().getStringExtra("act_id");

        TextView tv_activityName = findViewById(R.id.tv_activityName);
        TextView tv_startDate = findViewById(R.id.tv_startDate);
        TextView tv_EndDate = findViewById(R.id.tv_EndDate);
        TextView tv_activityAddress = findViewById(R.id.tv_activityAddress);
        TextView tv_activityDes = findViewById(R.id.tv_activityDes);
        TextView tv_activityType = findViewById(R.id.tv_activityType);

        tv_activityName.setText(aName);
        tv_activityType.setText(aType);
        tv_startDate.setText(aStartDate);
        tv_EndDate.setText(aEndDate);
        tv_activityAddress.setText(aAddress);
        tv_activityDes.setText(aDes);

        addActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetailsActivity.this,AddActActivity.class);
                startActivity(intent);
            }
        });

        btn_editAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDetailsActivity.this,EditActActivity.class);
                intent.putExtra("act_name", aName);
                intent.putExtra("act_type", aType);
                intent.putExtra("act_des", aDes);
                intent.putExtra("act_address", aAddress);
                intent.putExtra("act_startDateTime", aStartDate);
                intent.putExtra("act_endDateTime", aEndDate);
                intent.putExtra("act_id", aId);

                startActivity(intent);
            }
        });
    }
}
