package com.example.tripplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTrip extends AppCompatActivity {

    EditText destination, startDate,endDate,tripName, description;
    Button saveBtn;
    DatabaseReference databaseTrip;

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
                showDateTimeDialog(startDate);
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(endDate);
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
        databaseTrip.child("Trip").child(id).setValue(trip)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddTrip.this,"User details inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void showDateTimeDialog(final EditText date) {

        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        date.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(AddTrip.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), false).show();

            }
        };
        new DatePickerDialog(AddTrip.this,dateSetListener,calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
