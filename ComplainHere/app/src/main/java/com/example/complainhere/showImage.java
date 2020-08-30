package com.example.complainhere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class showImage extends AppCompatActivity {
 SqliteHelper objectDatabaseHandler;
 RecyclerView recyclerView;
 RVAdapter rvAdapter;
 ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(showImage.this, MainActivity.class);
                startActivity(i);
            }
        });
        recyclerView = findViewById(R.id.imageRV);
        objectDatabaseHandler = new SqliteHelper(this);

        rvAdapter = new RVAdapter(objectDatabaseHandler.getAllImagesData());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvAdapter);
  }

}
