package com.example.tripplannerapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RestaurantAdd extends AppCompatActivity {

    ///copy
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;

    EditText R_name, R_startDate,R_endDate,R_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        //////////////database

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        /////copy
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        userRef = db.getInstance().getReference();

        R_name = findViewById(R.id.R_name);
        R_startDate = findViewById(R.id.R_startDate);
        R_endDate = findViewById(R.id.R_endDate);
        R_address = findViewById(R.id.R_address);

        Button addBtn = findViewById(R.id.btn_addRestaurant);
        ImageButton btn_back = findViewById(R.id.btn_back);
        Button R_suggest = findViewById(R.id.buttonSuggest);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantAdd.this, RestaurantList.class);
                startActivity(intent);
            }
        });

        R_suggest.setOnClickListener(new View.OnClickListener() {
            //implicit intent
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.tripadvisor.com.my/Restaurants"));
                startActivity(i);
            }
        });




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InsertData();
                Intent intent = new Intent(RestaurantAdd.this,RestaurantList.class);
                startActivity(intent);

            }
        });

        R_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(R_startDate);
            }
        });
        R_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(R_endDate);
            }
        });
    }

    private void InsertData() {
        String name = R_name.getText().toString();
        String startDate = R_startDate.getText().toString();
        String endDate = R_endDate.getText().toString();
        String address = R_address.getText().toString();

        String id = userRef.push().getKey();

        RestaurantData R_data = new RestaurantData(name,startDate,endDate,address,id);
        userRef.child("Users").child(uid).child("RestaurantData").child(id).setValue(R_data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RestaurantAdd.this,"User details inserted", Toast.LENGTH_SHORT).show();
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

                new TimePickerDialog(RestaurantAdd.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), false).show();

            }
        };
        new DatePickerDialog(RestaurantAdd.this,dateSetListener,calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}
