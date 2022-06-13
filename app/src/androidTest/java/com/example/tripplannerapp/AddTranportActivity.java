package com.example.tripplannerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddTranportActivity extends AppCompatActivity {
    EditText transportName,Torderdate,Tprice,Tquantity;
    Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tranport);
        Torderdate =(EditText)findViewById(R.id.txtOrderDate);
        Tprice =(EditText) findViewById(R.id.txtRoomType);
        Tquantity= (EditText) findViewById(R.id.txtQuantity);
        transportName =(EditText) findViewById(R.id.txtTName);
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
        map.put("transportName",transportName.getText().toString());
        map.put("orderDate",Torderdate.getText().toString());
        map.put("Price",Tprice.getText().toString());
        map.put("quantity",Tquantity.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Transport").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddTranportActivity.this,"Booking is created successfully.",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddTranportActivity.this,"Error while creation",Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
}
