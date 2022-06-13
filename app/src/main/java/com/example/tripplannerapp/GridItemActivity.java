package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GridItemActivity extends AppCompatActivity {
    Button cancel;
    TextView name;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);
        cancel=(Button) findViewById(R.id.button);
        name = findViewById(R.id.griddata);
        image = findViewById(R.id.imageView);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image",0));
        cancel.setOnClickListener(new View.OnClickListener() {
            //explicit intent
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GridItemActivity.this,Transportation.class);
                startActivity(i);
            }
        });
    }

}