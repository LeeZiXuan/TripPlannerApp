package com.example.tripplannerapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Space;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class flight extends AppCompatActivity {
    TextInputEditText name, Departure , PhoneNo;
    MaterialButton addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        AppCompatSpinner spin = findViewById(R.id.Class);
        ArrayList<String> list = new ArrayList<>();
        list.add("Economy Class");
        list.add("Business Class");
        list.add("First Class");

        final ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(flight.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                name = findViewById(R.id.inputName);
                Departure = findViewById(R.id.PlaceDepart);
                PhoneNo = findViewById(R.id.TelephoneNo);
                addbtn = findViewById(R.id.btnCheckout);
                addbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });

    }
}