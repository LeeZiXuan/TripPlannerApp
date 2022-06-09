package com.example.tripplannerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class notificationList extends AppCompatActivity {
    
    RecyclerView recyclerView;
    AddNotification addNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        
        recyclerView = (RecyclerView) findViewById(R.id.notificationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Trip> options =
                new FirebaseRecyclerOptions.Builder<Trip>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"),Trip.class)
                        .build();

        addNotification = new AddNotification(options);
        recyclerView.setAdapter(addNotification);
    }

    @Override
    protected void onStart(){
        super.onStart();
        addNotification.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        addNotification.stopListening();
    }
}