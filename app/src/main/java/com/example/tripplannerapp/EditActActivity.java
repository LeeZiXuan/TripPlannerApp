package com.example.tripplannerapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditActActivity extends AppCompatActivity {

    EditText et_activityName;
    EditText et_activityType;
    EditText et_activity_address;
    EditText et_activity_des;

    EditText start_date_time_in;
    EditText end_date_time_in;

    Button btn_saveAct;
    Button btn_delete;

    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference ActRef;
    FirebaseUser user;
    String uid;
    String aId;

    String _ACTNAME, _ACTTYPE, _ACTSTARTDATE, _ACTENDDATE, _ACTADDRESS, _ACTDES;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        ActRef = db.getInstance().getReference();


        et_activityName = (findViewById(R.id.et_activityName));
        et_activityType = (findViewById(R.id.tv_activityType));
        et_activity_address = (findViewById(R.id.et_activity_address));
        et_activity_des = (findViewById(R.id.et_activity_des));

        start_date_time_in =findViewById(R.id.et_activity_startdatetime);
        start_date_time_in.setInputType(InputType.TYPE_NULL);

        end_date_time_in =findViewById(R.id.et_activity_enddatetime);
        end_date_time_in.setInputType(InputType.TYPE_NULL);

        btn_delete = findViewById(R.id.btn_delete);
        btn_saveAct = findViewById(R.id.btn_saveAct);

        String aName = getIntent().getStringExtra("act_name");
        String aType = getIntent().getStringExtra("act_type");
        String aStartDate = getIntent().getStringExtra("act_startDateTime");
        String aEndDate = getIntent().getStringExtra("act_endDateTime");
        String aAddress = getIntent().getStringExtra("act_address");
        String aDes = getIntent().getStringExtra("act_des");
        aId = getIntent().getStringExtra("act_id");


        ActRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                _ACTNAME = snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_name").getValue(String.class);
                _ACTTYPE = snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_type").getValue(String.class);
                _ACTSTARTDATE = snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_startDateTime").getValue(String.class);
                _ACTENDDATE = snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_endDateTime").getValue(String.class);
                _ACTADDRESS = snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_address").getValue(String.class);
                _ACTDES = snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_des").getValue(String.class);


                et_activityName.setText(snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_name").getValue(String.class));
                et_activityType.setText(snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_type").getValue(String.class));
                start_date_time_in.setText(snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_startDateTime").getValue(String.class));
                end_date_time_in.setText(snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_endDateTime").getValue(String.class));
                et_activity_address.setText(snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_address").getValue(String.class));
                et_activity_des.setText(snapshot.child("Users").child(uid).child("Activity").child(aId).child("act_des").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_saveAct.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View view) {

                if (isActNameChanged() || isActTypeChanged() || isActStartDateChanged() || isActEndDateChanged() || isActAddressChanged() || isActDesChanged()){
                    Toast.makeText(EditActActivity.this,"Data has been updated", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(EditActActivity.this,"Data is same and can not be updated", Toast.LENGTH_LONG).show();

                }
               Intent intent = new Intent(EditActActivity.this, ActivityListActivity.class);
               startActivity(intent);

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


    private boolean isActNameChanged(){
        if(!_ACTNAME.equals(et_activityName.getText().toString())){
            ActRef.child("Users").child(uid).child("Activity").child(aId).child("act_name").setValue(et_activityName.getText().toString());
            _ACTNAME = et_activityName.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isActTypeChanged(){
        if(!_ACTTYPE.equals(et_activityType.getText().toString())){
            ActRef.child("Users").child(uid).child("Activity").child(aId).child("act_type").setValue(et_activityType.getText().toString());
            _ACTTYPE = et_activityType.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isActStartDateChanged(){
        if(!_ACTSTARTDATE.equals(start_date_time_in.getText().toString())){
            ActRef.child("Users").child(uid).child("Activity").child(aId).child("act_startDateTime").setValue(start_date_time_in.getText().toString());
            _ACTSTARTDATE = start_date_time_in.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isActEndDateChanged(){
        if(!_ACTENDDATE.equals(end_date_time_in.getText().toString())){
            ActRef.child("Users").child(uid).child("Activity").child(aId).child("act_endDateTime").setValue(end_date_time_in.getText().toString());
            _ACTENDDATE = end_date_time_in.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isActAddressChanged(){
        if(!_ACTADDRESS.equals(et_activity_address.getText().toString())){
            ActRef.child("Users").child(uid).child("Activity").child(aId).child("act_address").setValue(et_activity_address.getText().toString());
            _ACTADDRESS = et_activity_address.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isActDesChanged(){
        if(!_ACTDES.equals(et_activity_des.getText().toString())){
            ActRef.child("Users").child(uid).child("Activity").child(aId).child("act_des").setValue(et_activity_des.getText().toString());
            _ACTDES = et_activity_des.getText().toString();
            return true;
        }
        else {
            return false;
        }
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

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime() ));
                    }
                };

                new TimePickerDialog(EditActActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();

            }
        };

        new DatePickerDialog(EditActActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


}
