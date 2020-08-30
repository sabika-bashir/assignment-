package com.example.complainhere;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {
   Long Id;
   ImageView image;
   TextView category,name,email,description,severity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
      //  category.findViewById(R.id.category);
        //description.findViewById(R.id.description);
        //severity.findViewById(R.id.severity);
        //image.findViewById(R.id.image);
        //name.findViewById(R.id.person);
        //email.findViewById(R.id.email);

        Intent intent = getIntent();
        Id = intent.getLongExtra("ID",0);
        Toast.makeText(this,"id= " +Id ,Toast.LENGTH_SHORT).show();


    }
}
