package com.example.tripplannerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class Hotel extends AppCompatActivity {

    Button clk;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        clk=(Button) findViewById(R.id.btn_booking);
        Button btn = findViewById(R.id.btn_review);
        btn.setOnClickListener(new View.OnClickListener() {
            //implicit intent
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.booking.com/reviews/country/my.en-gb.html?aid=356980&label=gog235jc-1DEg5yZXZpZXdzY291bnRyeSihATgKSDNYA2ihAYgBAZgBCbgBF8gBDNgBA-gBAYgCAagCA7gC65OOlQbAAgHSAiQ3OTY5YWY5OS01OWVkLTQ0MzItYWU5MS1jNmMzNWZmNTYxMznYAgTgAgE&sid=fb3fb3b74bbec61e441187f51ecdf9d1&keep_landing=1&type=all&"));
                startActivity(i);
            }
        });

        clk.setOnClickListener(new View.OnClickListener() {
            //explicit intent
            @Override
            public void onClick(View view) {
                Intent jumppage = new Intent(Hotel.this,Room.class);
                startActivity(jumppage);
            }
        });
    }

}

