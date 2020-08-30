package com.example.complainhere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_PER_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    TextView name , email;
    Button addComplain ,viewComplain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name =  findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        String name = getIntent().getStringExtra("username");
        email.setText(name);
        addComplain = findViewById(R.id.addComplain);
        viewComplain = findViewById(R.id.view_complain);
        addComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, complaininfo.class);
                startActivity(i);
            }
        });
        viewComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, showImage.class);
                startActivity(i);
            }
        });
    }



}
