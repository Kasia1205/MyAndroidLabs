package com.example.myandroidlabs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String emailAddress = "emailKey";
    EditText email;
    ImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent fromMain = getIntent();
        fromMain.getStringExtra(emailAddress);
        email= findViewById(R.id.typeEmail);
        email.setText(fromMain.getStringExtra(emailAddress));
        mImageButton = findViewById(R.id.pictureButton);

        mImageButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
        // Log.e(ACTIVITY_NAME, "In function: " + "onActivityResult()");
    }
    @Override
    protected void onStart() {

        super.onStart();
        // Log.e(ACTIVITY_NAME, "In function: " + "onStart()");
    }
    @Override
    protected void onResume() {

        super.onResume();
        // Log.e(ACTIVITY_NAME, "In function: " + "onResume()");
    }
    @Override
    protected void onPause() {

        super.onPause();
        //Log.e(ACTIVITY_NAME, "In function: " + "onPause()");
    }
    @Override
    protected void onStop() {

        super.onStop();
        //Log.e(ACTIVITY_NAME, "In function: " + "onCreate()");
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        //Log.e(ACTIVITY_NAME, "In function: " + "onDestroy()");
    }
}