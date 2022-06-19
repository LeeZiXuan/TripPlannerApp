package com.example.tripplannerapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class Profile2Fragment extends Fragment {


    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;

    ImageView img_profilePic;

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
        ImageView img_profilePic = (ImageView) view.findViewById(R.id.img_profilePic);

        ImageButton btn_editProfile = (ImageButton) view.findViewById(R.id.btn_editAct);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tv_username.setText(snapshot.child("Users").child(uid).child("username").getValue(String.class));
                tv_phone.setText(snapshot.child("Users").child(uid).child("phoneNum").getValue(String.class));
                tv_email.setText(snapshot.child("Users").child(uid).child("email").getValue(String.class));
                tv_pass.setText(snapshot.child("Users").child(uid).child("password").getValue(String.class));

                String value = snapshot.child("Users").child(uid).child("ProfilePic").child("imageUri").getValue(String.class);
                Picasso.get().load(value).into(img_profilePic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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