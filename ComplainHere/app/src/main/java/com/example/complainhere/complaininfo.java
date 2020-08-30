package com.example.complainhere;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class complaininfo extends AppCompatActivity {
    public static final int CAMERA_PER_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    ImageView setimage ,backButton;
    Button  saveimage ;
    EditText description , category , severity;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaininfo);
        setimage = findViewById(R.id.setImage);
        saveimage = findViewById(R.id.saveimage);
        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        severity = findViewById(R.id.severity);
        backButton = findViewById(R.id.backButton);

        //final String  email = getIntent().getStringExtra("username");
        final  String email = "sabika@gmail.com";
        final String name = "sabika";
        sqliteHelper = new SqliteHelper(this);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(complaininfo.this, MainActivity.class);
                startActivity(i);
            }
        });
        setimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }
        });
           saveimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Bitmap image = ((BitmapDrawable)setimage.getDrawable()).getBitmap();
                complain complain = new complain(name,email,description.getText().toString(),category.getText().toString(),severity.getText().toString(),image);
                 sqliteHelper.addcomplain(complain);
                Toast.makeText(getApplicationContext(),"Post Upload successfully!",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PER_CODE);
        }
        else
        {
            dispatchTakePictureIntent();
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK)
            {
                File f =new File(currentPhotoPath);
                setimage.setImageURI(Uri.fromFile(f));

            }
            Bundle extras = data.getExtras();
           Bitmap image = (Bitmap)extras.get("data");
           setimage.setImageBitmap(image);
        }
    }
    String currentPhotoPath;
    private File createImageFile() throws IOException
    {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
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
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }
}