package com.example.tripplannerapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActActivity extends AppCompatActivity {



    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;

    EditText et_activityName;
    EditText et_activityType;
    EditText et_activity_address;
    EditText et_activity_des;

    EditText start_date_time_in;
    EditText end_date_time_in;

    Button btn_addActivity;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        //////////////database

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        /////copy
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        userRef = db.getInstance().getReference();

        /////////////////////////EditText
        et_activityName = (findViewById(R.id.et_activityName));
        et_activityType = (findViewById(R.id.et_activityType));
        et_activity_address = (findViewById(R.id.et_activity_address));
        et_activity_des = (findViewById(R.id.et_activity_des));

        start_date_time_in =findViewById(R.id.et_activity_startdatetime);
        start_date_time_in.setInputType(InputType.TYPE_NULL);

        end_date_time_in =findViewById(R.id.et_activity_enddatetime);
        end_date_time_in.setInputType(InputType.TYPE_NULL);

        Button btn_addActivity = findViewById(R.id.btn_addActivity);
        /////////////////////////

        btn_addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activityName = et_activityName.getText().toString().trim();
                String activityType = et_activityType.getText().toString().trim();
                String activity_address = et_activity_address.getText().toString().trim();
                String activity_des = et_activity_des.getText().toString().trim();
                String start_date_time = start_date_time_in.getText().toString().trim();
                String end_date_time = end_date_time_in.getText().toString().trim();

                if(TextUtils.isEmpty(activityName)){
                    et_activityName.setError("Activity Name is required");
                    return;
                }
                if(TextUtils.isEmpty(activityType)){
                    et_activityType.setError("Activity Type is required");
                    return;
                }
                if(TextUtils.isEmpty(activity_address)){
                    et_activity_address.setError("Activity Address is required");
                    return;
                }
                if(TextUtils.isEmpty(activity_des)){
                    et_activity_des.setError("Activity Description is required");
                    return;
                }
                if(TextUtils.isEmpty(start_date_time)){
                    start_date_time_in.setError("Activity Start Date and Time is required");
                    return;
                }
                if(TextUtils.isEmpty(end_date_time)){
                    end_date_time_in.setError("Activity End Date and Time is required");
                    return;
                }

                /////copy
                Activity activity = new Activity(activityName,activityType,activity_address,activity_des,start_date_time,end_date_time);

                db.getReference().child("Users").child(uid).child("Activity").setValue(activity).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(AddActActivity.this,"Activity details inserted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        start_date_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(start_date_time_in);

            }
        });

        end_date_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(end_date_time_in);

            }
        });
    }

    private void showDateTimeDialog(final EditText date_time_in) {

        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime() ));
                    }
                };

                new TimePickerDialog(AddActActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();

            }
        };

        new DatePickerDialog(AddActActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


}
