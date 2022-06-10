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

public class ActivityListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addActivityBtn;
    ArrayList<Activity> Actlist;
    ActivityAdapter activityAdapter;

    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    FirebaseUser user;
    String uid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_activity);

        recyclerView = findViewById(R.id.recycleViewActivity);
        addActivityBtn = findViewById(R.id.addActivityBtn);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Activity");

        Actlist = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityAdapter = new ActivityAdapter(this, Actlist, this::onItemClick);
        recyclerView.setAdapter(activityAdapter);

        db.getReference().child("Users").child(uid).child("Activity").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            Activity activity = dataSnapshot.getValue(Activity.class);
                            Actlist.add(activity);
                        }
                        activityAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });

        addActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityListActivity.this, AddActActivity.class);
                startActivity(intent);
            }
        });

    }


    public void onItemClick(int position) {
        Intent intent = new Intent(this,ActivityDetailsActivity.class);

        intent.putExtra("act_name", Actlist.get(position).getAct_name());
        intent.putExtra("act_type", Actlist.get(position).getAct_type());
        intent.putExtra("act_des", Actlist.get(position).getAct_des());
        intent.putExtra("act_address", Actlist.get(position).getAct_address());
        intent.putExtra("act_startDateTime", Actlist.get(position).getAct_startDateTime());
        intent.putExtra("act_endDateTime", Actlist.get(position).getAct_endDateTime());
        intent.putExtra("act_id", Actlist.get(position).getAct_id());

        startActivity(intent);
    }

}
