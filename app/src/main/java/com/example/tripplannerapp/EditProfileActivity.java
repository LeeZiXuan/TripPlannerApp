package com.example.tripplannerapp;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditProfileActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int CAMERA_PERM_CODE = 101;
    public static final int GALLERY_REQUEST_CODE = 105;
    public static final int STORAGE_PERM_CODE = 103;

    String currentPhotoPath;

    ImageView selectedImage;
    ImageView selectedImagetest;
    ImageButton btn_camera;
    ImageButton btn_gallery;
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
    Uri profilePicUrl;


    StorageReference storageReference;

    String _USERNAME, _EMAIL, _PHONE, _PASS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //////////////database

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();

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
        btn_saveProfile = findViewById(R.id.btn_saveAct);

        selectedImage = findViewById(R.id.img_profile);
        selectedImagetest = findViewById(R.id.img_profilePic);

        btn_camera = findViewById(R.id.btn_camera);
        btn_gallery = findViewById(R.id.btn_gallery);

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


                String value = snapshot.child("Users").child(uid).child("ProfilePic").child("imageUri").getValue(String.class);
                Picasso.get().load(value).into(selectedImage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermissions();
            }
        });

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQUEST_CODE);
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
            //openCamera();
            dispatchTakePictureIntent();
        }

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                //openCamera();
                dispatchTakePictureIntent();

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
            //Bitmap image = (Bitmap) data.getExtras().get("data");
            //selectedImagetest.setImageBitmap(image);
            if(resultCode == Activity.RESULT_OK) {
                File f = new File(currentPhotoPath);
                //selectedImage.setImageURI(Uri.fromFile(f));
                Log.d("tag", "Absolute Url of Image is " + Uri.fromFile(f));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

                uploadImageToFirebase(f.getName(), contentUri);
            }
        }

        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp +"."+getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Gallery Image Uri:  " +  imageFileName);
                //selectedImage.setImageURI(contentUri);

                uploadImageToFirebase(imageFileName,contentUri);


            }

        }
    }

    private void uploadImageToFirebase(String name, Uri contentUri) {
        final StorageReference image = storageReference.child("pictures/" + name);
        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Picasso.get().load(uri).into(selectedImage);
                        profilePicUrl = uri;


                        ProfilePic profilePic = new ProfilePic(profilePicUrl.toString());
                        userRef.child("Users").child(uid).child("ProfilePic").setValue(profilePic);

                    }
                });

                Toast.makeText(EditProfileActivity.this, "Image Is Uploaded.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfileActivity.this, "Upload Failled.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "Create File Fail.", Toast.LENGTH_SHORT).show();


            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        //}
    }



    public void update(View view)
    {
        if (isNameChanged() || isEmailChanged() || isPhoneChanged() || isPasswordChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_LONG).show();


            Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
            startActivity(intent);
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

            return true;
        }
        else {
            return false;
        }
    }

}
