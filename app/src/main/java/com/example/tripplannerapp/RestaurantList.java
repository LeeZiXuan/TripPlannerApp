package com.example.tripplannerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestaurantList extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addActivityBtn;
    ArrayList<RestaurantData> R_list;
    R_Adapter R_adapter;

    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    FirebaseUser user;
    String uid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_recycler);

        recyclerView = findViewById(R.id.restaurantList);
        addActivityBtn = findViewById(R.id.addActivityBtn);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("RestaurantData");

        R_list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        R_adapter = new R_Adapter(this, R_list, this::onItemClick);
        recyclerView.setAdapter(R_adapter);

        db.getReference().child("Users").child(uid).child("RestaurantData").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            RestaurantData R_data = dataSnapshot.getValue(RestaurantData.class);
                            R_list.add(R_data);
                        }
                        R_adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });

        addActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantList.this, RestaurantAdd.class);
                startActivity(intent);
            }
        });

    }


    public void onItemClick(int position) {
        Intent intent = new Intent(this,RestaurantDetailsActivity.class);

        intent.putExtra("r_id", R_list.get(position).getR_id());
        intent.putExtra("r_startDate", R_list.get(position).getR_startDate());
        intent.putExtra("r_endDate", R_list.get(position).getR_endDate());
        intent.putExtra("r_address", R_list.get(position).getR_address());
        intent.putExtra("r_dataID", R_list.get(position).getR_dataID());

        startActivity(intent);
    }

}
