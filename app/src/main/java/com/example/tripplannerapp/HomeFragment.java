package com.example.tripplannerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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


public class HomeFragment extends Fragment implements TripRecyclerViewInterface {

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    ArrayList<Trip> list;
    MyAdapter adapter;

    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    FirebaseUser user;
    String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewTrip);
        addButton = (FloatingActionButton) view.findViewById(R.id.addTripBtn);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Trip");

        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyAdapter(getActivity(), list, this::onItemClick);
        recyclerView.setAdapter(adapter);

        db.getReference().child("Users").child(uid).child("Trip").
            addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Trip trip = dataSnapshot.getValue(Trip.class);
                    list.add(trip);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddTrip.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(),Plan.class);

        intent.putExtra("Trip name", list.get(position).getTripName());
        intent.putExtra("Start Date", list.get(position).getStartDate());
        intent.putExtra("End Date", list.get(position).getEndDate());

        startActivity(intent);
    }
}