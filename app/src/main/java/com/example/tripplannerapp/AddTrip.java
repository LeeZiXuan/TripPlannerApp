package com.example.tripplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTrip extends AppCompatActivity {

    EditText destination, startDate,endDate,tripName, description;
    Button saveBtn;
    DatabaseReference databaseTrip;

    FirebaseAuth fAuth;
    FirebaseDatabase db;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        destination = findViewById(R.id.destination);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        tripName = findViewById(R.id.tripName);
        description = findViewById(R.id.description);
        saveBtn = findViewById(R.id.saveBtn);

        //Date picker
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        //database
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseTrip = FirebaseDatabase.getInstance().getReference();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        startDate.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        endDate.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });
    }

    private void InsertData() {
        String des = destination.getText().toString();
        String start = startDate.getText().toString();
        String end = endDate.getText().toString();
        String tname = tripName.getText().toString();
        String desc = description.getText().toString();
        String id = databaseTrip.push().getKey();

        Trip trip = new Trip(des,start,end,tname,desc);
        db.getReference().child("Users").child(uid).child("Trip").child(id).setValue(trip)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddTrip.this,"User details inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddTrip.this,MainActivity.class));
                            finish();
                        }
                    }
                });
    }
}
