package com.example.tripplannerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST_CODE = 102;

    public static final int CAMERA_PERM_CODE = 101;
    ImageView selectedImage;
    ImageButton btn_camera;
    ImageButton btn_backToProfile;
    Button btn_saveProfile;

    EditText et_username;
    EditText et_email;
    EditText et_phone;
    EditText et_pass;

    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;

    String _USERNAME, _EMAIL, _PHONE, _PASS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //////////////database

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        /////copy
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        userRef = db.getInstance().getReference();
        ///

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_pass = findViewById(R.id.et_pass);

        btn_backToProfile = findViewById(R.id.btn_backToProfile);
        btn_saveProfile = findViewById(R.id.btn_saveProfile);

        selectedImage = findViewById(R.id.img_profile);
        btn_camera = findViewById(R.id.btn_camera);

        /////showUserProfile
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                _USERNAME = snapshot.child("Users").child(uid).child("username").getValue(String.class);
                _PASS= snapshot.child("Users").child(uid).child("password").getValue(String.class);
                _EMAIL= snapshot.child("Users").child(uid).child("email").getValue(String.class);
                _PHONE= snapshot.child("Users").child(uid).child("phoneNum").getValue(String.class);

                et_username.setText(snapshot.child("Users").child(uid).child("username").getValue(String.class));
                et_phone.setText(snapshot.child("Users").child(uid).child("phoneNum").getValue(String.class));
                et_email.setText(snapshot.child("Users").child(uid).child("email").getValue(String.class));
                et_pass.setText(snapshot.child("Users").child(uid).child("password").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
  /*
        btn_saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();

                bundle.putString("ProfilePic", getIntent().getStringExtra("ProfilePic"));


                Profile2Fragment profile2Fragment = new Profile2Fragment();
                profile2Fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,profile2Fragment).commit();


            }
        });
        */

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermissions();

            }
        });

        btn_backToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToProfile();
            }
        });

    }

    public void backToProfile(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void askCameraPermissions(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else {
            openCamera();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera Permission is Required to Use Camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera(){

        Intent camera = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
        startActivityForResult(camera, CAMERA_REQUEST_CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            selectedImage.setImageBitmap(image);
        }
    }

    public void update(View view)
    {
        if (isNameChanged() || isEmailChanged() || isPhoneChanged() || isPasswordChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_LONG).show();
            backToProfile();
        }
        else {
            Toast.makeText(this,"Data is same and can not be updated", Toast.LENGTH_LONG).show();

        }

    }

    private boolean isNameChanged(){
        if(!_USERNAME.equals(et_username.getText().toString())){
            userRef.child("Users").child(uid).child("username").setValue(et_username.getText().toString());
            _USERNAME = et_username.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if(!_PASS.equals(et_pass.getText().toString())){
            userRef.child("Users").child(uid).child("password").setValue(et_pass.getText().toString());
            _PASS = et_pass.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if(!_EMAIL.equals(et_email.getText().toString())){
            userRef.child("Users").child(uid).child("email").setValue(et_email.getText().toString());
            _EMAIL = et_email.getText().toString();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isPhoneChanged() {
        if(!_PHONE.equals(et_phone.getText().toString())){
            userRef.child("Users").child(uid).child("phoneNum").setValue(et_phone.getText().toString());
            _PHONE = et_phone.getText().toString();

            Toast.makeText(this,"success", Toast.LENGTH_LONG).show();

            return true;
        }
        else {
            return false;
        }
    }

}
