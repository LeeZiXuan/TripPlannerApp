package com.example.tripplannerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EditProfileActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST_CODE = 102;

    public static final int CAMERA_PERM_CODE = 101;
    ImageView selectedImage;
    ImageButton btn_camera;
    ImageButton btn_backToProfile;
    Button btn_addActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btn_backToProfile = findViewById(R.id.btn_backToProfile);
        btn_addActivity = findViewById(R.id.btn_addActivity);

        selectedImage = findViewById(R.id.img_profile);
        btn_camera = findViewById(R.id.btn_camera);

        btn_addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle bundle = new Bundle();

                bundle.putString("ProfilePic", getIntent().getStringExtra("ProfilePic"));


                Profile2Fragment profile2Fragment = new Profile2Fragment();
                profile2Fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container,profile2Fragment).commit();

                 */
            }
        });

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST_CODE){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            selectedImage.setImageBitmap(image);
        }
    }
}
