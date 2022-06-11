package com.example.tripplannerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddHotelActivity extends AppCompatActivity {

    EditText hotelName,orderdate,roomType,quantity;
    Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        orderdate =(EditText)findViewById(R.id.txtOrderDate);
        roomType =(EditText) findViewById(R.id.txtRoomType);
        quantity= (EditText) findViewById(R.id.txtQuantity);
        hotelName =(EditText) findViewById(R.id.txtHotelName);
        btnadd =(Button) findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }
    private void insertData(){
        Map<String,Object> map =new HashMap<>();
        map.put("hotelName",hotelName.getText().toString());
        map.put("orderDate",orderdate.getText().toString());
        map.put("roomType",roomType.getText().toString());
        map.put("quantity",quantity.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Hotels").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddHotelActivity.this,"Booking is created successfully.",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddHotelActivity.this,"Error while creation",Toast.LENGTH_SHORT).show();
                    }
                });

    }
}