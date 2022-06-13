package com.example.tripplannerapp;



import android.content.Intent;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Transportation extends AppCompatActivity {

    GridView gridView;
    ImageButton bulb;
    String[] transportNames = {"Bus", "Plane", "MRT","Railway", "Bike","Motorcycle","Ship"};
    int[] transImages ={R.drawable.bus,R.drawable.plane,R.drawable.mrt,R.drawable.railway,R.drawable.bike,R.drawable.motorcycle,R.drawable.ship};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);
        bulb=(ImageButton) findViewById(R.id.bulb);
        gridView =findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l) {
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name", transportNames[i]);
                intent.putExtra("image",transImages[i]);
                startActivity(intent);
            }
        });

        bulb.setOnClickListener(new View.OnClickListener() {
            //explicit intent
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Transportation.this,LightSensorActivity.class);
                startActivity(i);
            }
        });
    }

    private class CustomAdapter extends BaseAdapter{
        public int getCount(){
            return transportNames.length;
        }

        public Object getItem(int i){
            return null;
        }

        public long getItemId(int i){
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup){
           View view1 = getLayoutInflater().inflate(R.layout.grid_item,null);
            TextView name= view1.findViewById(R.id.transport);
            TextView details = view1.findViewById(R.id.details);
            ImageView image = view1.findViewById(R.id.image);
            name.setText(transportNames[i]);
            image.setImageResource(transImages[i]);
            return view1;
        }
    }
}