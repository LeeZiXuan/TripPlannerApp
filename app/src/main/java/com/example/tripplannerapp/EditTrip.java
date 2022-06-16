package com.example.tripplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditTrip extends AppCompatActivity {

    EditText _destination;
    EditText _startDate;
    EditText _endDate;
    EditText _tripName;
    EditText _description;

    Button updateBtn;
    Button deleteBtn;

    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference ActRef;
    FirebaseUser user;
    String uid;
    String aId;

    String _DES, _SDATE, _EDATE, _TNAME, _DESC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        ActRef = db.getInstance().getReference();

        _destination = (findViewById(R.id._destination));
        _tripName = (findViewById(R.id._tripName));
        _description = (findViewById(R.id._description));
        _startDate =(findViewById(R.id._startDate));
        _endDate = (findViewById(R.id._endDate));

        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        String tDes = getIntent().getStringExtra("destination");
        String sDate = getIntent().getStringExtra("startDate");
        String eDate = getIntent().getStringExtra("endDate");
        String tName = getIntent().getStringExtra("tripName");
        String tDesc = getIntent().getStringExtra("description");
        aId = getIntent().getStringExtra("trip_id");

        ActRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                _DES = snapshot.child("Users").child(uid).child("Trip").child(aId).child("destination").getValue(String.class);
                _SDATE = snapshot.child("Users").child(uid).child("Trip").child(aId).child("startDate").getValue(String.class);
                _EDATE = snapshot.child("Users").child(uid).child("Trip").child(aId).child("endDate").getValue(String.class);
                _TNAME = snapshot.child("Users").child(uid).child("Trip").child(aId).child("tripName").getValue(String.class);
                _DESC = snapshot.child("Users").child(uid).child("Trip").child(aId).child("description").getValue(String.class);


                _destination.setText(snapshot.child("Users").child(uid).child("Trip").child(aId).child("destination").getValue(String.class));
                _startDate.setText(snapshot.child("Users").child(uid).child("Trip").child(aId).child("startDate").getValue(String.class));
                _endDate.setText(snapshot.child("Users").child(uid).child("Trip").child(aId).child("endDate").getValue(String.class));
                _tripName.setText(snapshot.child("Users").child(uid).child("Trip").child(aId).child("tripName").getValue(String.class));
                _description.setText(snapshot.child("Users").child(uid).child("Trip").child(aId).child("description").getValue(String.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Set alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(EditTrip.this);
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted data can't be Undo.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.getReference().child("Users").child(uid).child("Trip").child(aId).removeValue();
                        Toast.makeText(EditTrip.this, "Deleted.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditTrip.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditTrip.this, "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTripDesChanged() || isTripStartDateChanged() || isTripEndDateChanged() || isTripNameChanged() || isTripDescChanged()){
                    Toast.makeText(EditTrip.this,"Data has been updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditTrip.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(EditTrip.this,"Data is same and can not be updated", Toast.LENGTH_LONG).show();
                }
            }
        });

        _startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(_startDate);
            }
        });

        _endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(_endDate);
            }
        });
    }
    private boolean isTripDesChanged(){
        if(!_DES.equals(_destination.getText().toString())){
            ActRef.child("Users").child(uid).child("Trip").child(aId).child("destination").setValue(_destination.getText().toString());
            _DES = _destination.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isTripStartDateChanged(){
        if(!_SDATE.equals(_startDate.getText().toString())){
            ActRef.child("Users").child(uid).child("Trip").child(aId).child("startDate").setValue(_startDate.getText().toString());
            _SDATE = _startDate.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isTripEndDateChanged(){
        if(!_EDATE.equals(_endDate.getText().toString())){
            ActRef.child("Users").child(uid).child("Trip").child(aId).child("endDate").setValue(_endDate.getText().toString());
            _EDATE = _endDate.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isTripNameChanged(){
        if(!_TNAME.equals(_tripName.getText().toString())){
            ActRef.child("Users").child(uid).child("Trip").child(aId).child("tripName").setValue(_tripName.getText().toString());
            _TNAME = _tripName.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isTripDescChanged(){
        if(!_DESC.equals(_description.getText().toString())){
            ActRef.child("Users").child(uid).child("Trip").child(aId).child("description").setValue(_description.getText().toString());
            _DESC = _description.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private void showDateTimeDialog(final EditText date_in){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(EditTrip.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}