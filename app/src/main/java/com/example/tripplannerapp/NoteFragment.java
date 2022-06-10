package com.example.tripplannerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoteFragment extends Fragment {

    RecyclerView recyclerView;
    AddNotification addNotification; //adapter
    ArrayList<Trip> list;

    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    FirebaseUser user;
    String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.notificationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Trip");

        list = new ArrayList<>();

        addNotification = new AddNotification(getActivity(), list);

        recyclerView.setAdapter(addNotification);

        db.getReference().child("Users").child(uid).child("Trip").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Trip trip = dataSnapshot.getValue(Trip.class);
                            list.add(trip);
                        }
                        addNotification.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });


        return view;
    }

}