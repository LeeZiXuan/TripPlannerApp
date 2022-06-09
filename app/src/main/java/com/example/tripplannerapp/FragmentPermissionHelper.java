package com.example.tripplannerapp;

import android.Manifest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;

public class FragmentPermissionHelper {
    public void startpermissiononrequest(FragmentActivity fr, FragmentPermissionInterface fs,String manifest) {
        ActivityResultLauncher<String> requestPermissionLauncher =
                fr.registerForActivityResult(new ActivityResultContracts.RequestPermission(), fs::onGranted);

        requestPermissionLauncher.launch(
                manifest);

    }
}




