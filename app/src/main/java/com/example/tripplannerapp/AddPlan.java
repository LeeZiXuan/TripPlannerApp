package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddPlan extends AppCompatActivity implements View.OnClickListener {
 public CardView card1 , card2 , card3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);

        card1 = (CardView) findViewById(R.id.cvFlight);
        card2 = (CardView) findViewById(R.id.cvAccomodation);
        card3 = (CardView) findViewById(R.id.cvRestaurant);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.cvFlight:
                i = new Intent(this,flight.class);
                startActivity(i);
                break;

            case R.id.cvAccomodation:
                i = new Intent(this,Hotel.class);
                startActivity(i);
                break;
            case R.id.cvRestaurant:
                i = new Intent(this,RestaurantAdd.class);
                startActivity(i);
                break;
        }
    }
}