package com.example.tripplannerapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

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
import android.widget.Toast;


public class Profile2Fragment extends Fragment {

    private FragmentActivity frs;

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentActivity){
            frs=(FragmentActivity)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageButton btn_editProfile = (ImageButton) view.findViewById(R.id.btn_editProfile);


        //ImageView selectedImage = (ImageView) view.findViewById(R.id.img_profile);
        ImageButton btn_camera = (ImageButton) view.findViewById(R.id.btn_camera);

            btn_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    askCameraPermissions();

                }
            });





        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditProfileActivity.class);
                intent.putExtra("some", "some data");
                startActivity(intent);
            }
        });

        return view;

    }

    private void askCameraPermissions(){
        new FragmentPermissionHelper().startpermissiononrequest(frs, new FragmentPermissionInterface() {
            @Override
            public void onGranted(boolean isGranted) {

                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    openCamera();
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(getActivity(), "Camera Permission is Required to Use Camera.", Toast.LENGTH_SHORT).show();
                }

            }
        }, Manifest.permission.CAMERA);



    }

    /*public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera Permission is Required to Use Camera.", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    private void openCamera(){
        Toast.makeText(getActivity(), "Camera Permission Required", Toast.LENGTH_SHORT).show();

    }



}