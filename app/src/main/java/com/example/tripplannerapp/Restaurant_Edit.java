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

public class Restaurant_Edit extends AppCompatActivity {

    EditText et_R_name;
    EditText et_address;
    EditText et_start;
    EditText et_end;

    Button btn_save;
    Button btn_delete;

    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference ActRef;
    FirebaseUser user;
    String uid;
    String rId;

    String _ACTNAME, _ACTSTARTDATE, _ACTENDDATE, _ACTADDRESS;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_edit_2);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        ActRef = db.getInstance().getReference();


        et_R_name = (findViewById(R.id.et_R_name));
        et_address = (findViewById(R.id.et_address));

        et_start =findViewById(R.id.et_start);
        et_start.setInputType(InputType.TYPE_NULL);

        et_end =findViewById(R.id.et_end);
        et_end.setInputType(InputType.TYPE_NULL);

        btn_delete = findViewById(R.id.btn_delete);
        btn_save = findViewById(R.id.btn_save);

        String R_name = getIntent().getStringExtra("r_id");
        String R_startDate = getIntent().getStringExtra("r_startDate");
        String R_endDate = getIntent().getStringExtra("r_endDate");
        String R_address = getIntent().getStringExtra("r_address");
        rId = getIntent().getStringExtra("r_dataID");


        ActRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                _ACTNAME = snapshot.child("Users").child(uid).child("RestaurantData").child(rId).child("r_id").getValue(String.class);
                _ACTSTARTDATE = snapshot.child("Users").child(uid).child("RestaurantData").child(rId).child("r_startDate").getValue(String.class);
                _ACTENDDATE = snapshot.child("Users").child(uid).child("RestaurantData").child(rId).child("r_endDate").getValue(String.class);
                _ACTADDRESS = snapshot.child("Users").child(uid).child("RestaurantData").child(rId).child("r_address").getValue(String.class);


                et_R_name.setText(snapshot.child("Users").child(uid).child("RestaurantData").child(rId).child("r_id").getValue(String.class));
                et_address.setText(snapshot.child("Users").child(uid).child("RestaurantData").child(rId).child("r_address").getValue(String.class));
                et_start.setText(snapshot.child("Users").child(uid).child("RestaurantData").child(rId).child("r_startDate").getValue(String.class));
                et_end.setText(snapshot.child("Users").child(uid).child("RestaurantData").child(rId).child("r_endDate").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (isActNameChanged() || isActStartDateChanged() || isActEndDateChanged() || isActAddressChanged()){
                    Toast.makeText(Restaurant_Edit.this,"Data has been updated", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Restaurant_Edit.this,"Data is same and can not be updated", Toast.LENGTH_LONG).show();

                }
                Intent intent = new Intent(Restaurant_Edit.this, RestaurantList.class);
                startActivity(intent);

            }

        });

        et_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(et_start);

            }
        });

        et_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(et_end);

            }
        });



    }


    private boolean isActNameChanged(){
        if(!_ACTNAME.equals(et_R_name.getText().toString())){
            ActRef.child("Users").child(uid).child("RestaurantData").child(rId).child("r_id").setValue(et_R_name.getText().toString());
            _ACTNAME = et_R_name.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }


    private boolean isActStartDateChanged(){
        if(!_ACTSTARTDATE.equals(et_start.getText().toString())){
            ActRef.child("Users").child(uid).child("RestaurantData").child(rId).child("r_startDate").setValue(et_start.getText().toString());
            _ACTSTARTDATE = et_start.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isActEndDateChanged(){
        if(!_ACTENDDATE.equals(et_end.getText().toString())){
            ActRef.child("Users").child(uid).child("RestaurantData").child(rId).child("r_endDate").setValue(et_end.getText().toString());
            _ACTENDDATE = et_end.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isActAddressChanged(){
        if(!_ACTADDRESS.equals(et_address.getText().toString())){
            ActRef.child("Users").child(uid).child("RestaurantData").child(rId).child("r_address").setValue(et_address.getText().toString());
            _ACTADDRESS = et_address.getText().toString();
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

                new TimePickerDialog(Restaurant_Edit.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();

            }
        };

        new DatePickerDialog(Restaurant_Edit.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


}
