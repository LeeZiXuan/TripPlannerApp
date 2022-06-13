package com.example.tripplannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RestaurantDetailsActivity extends AppCompatActivity {

    FloatingActionButton btn_add;
    ImageButton btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        btn_add = findViewById(R.id.btn_add);
        btn_edit = findViewById(R.id.btn_edit);

        String R_name = getIntent().getStringExtra("r_id");
        String R_startDate = getIntent().getStringExtra("r_startDate");
        String R_endDate = getIntent().getStringExtra("r_endDate");
        String R_address = getIntent().getStringExtra("r_address");
        String R_dataID = getIntent().getStringExtra("r_dataID");

        TextView restaurant_name = findViewById(R.id.restaurant_name);
        TextView startDate = findViewById(R.id.startDate);
        TextView EndDate = findViewById(R.id.EndDate);
        TextView address = findViewById(R.id.address);

        restaurant_name.setText(R_name);
        startDate.setText(R_startDate);
        EndDate.setText(R_endDate);
        address.setText(R_address);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantDetailsActivity.this,RestaurantAdd.class);
                startActivity(intent);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantDetailsActivity.this,Restaurant_Edit.class);
                intent.putExtra("r_id", R_name);
                intent.putExtra("r_address", R_address);
                intent.putExtra("r_startDate", R_startDate);
                intent.putExtra("r_endDate", R_endDate);
                intent.putExtra("r_dataID", R_dataID);

                startActivity(intent);
            }
        });
    }
}
