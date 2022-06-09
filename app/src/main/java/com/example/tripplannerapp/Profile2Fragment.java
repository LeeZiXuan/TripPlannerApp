package com.example.tripplannerapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile2Fragment extends Fragment {


    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //////////////database
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        /////copy
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        userRef = db.getInstance().getReference();
        ///

        TextView tv_username = (TextView) view.findViewById(R.id.tv_username);
        TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        TextView tv_email = (TextView) view.findViewById(R.id.tv_email);
        TextView tv_pass = (TextView) view.findViewById(R.id.tv_pass);

        ImageButton btn_editProfile = (ImageButton) view.findViewById(R.id.btn_editProfile);
        ImageButton btn_addAct = (ImageButton) view.findViewById(R.id.btn_addAct);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //String user_name = snapshot.child(uid).child("username").getValue(String.class);
                tv_username.setText(snapshot.child("Users").child(uid).child("username").getValue(String.class));
                tv_phone.setText(snapshot.child("Users").child(uid).child("phoneNum").getValue(String.class));
                tv_email.setText(snapshot.child("Users").child(uid).child("email").getValue(String.class));
                tv_pass.setText(snapshot.child("Users").child(uid).child("password").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_addAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddActActivity.class);
                intent.putExtra("resId", R.drawable.ic_person);
                startActivity(intent);
            }
        });


        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditProfileActivity.class);
                intent.putExtra("resId", R.drawable.ic_person);
                startActivity(intent);
            }
        });

        return view;

    }


}