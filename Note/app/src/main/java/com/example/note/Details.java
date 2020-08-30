package com.example.note;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {
   String subject;
   TextView note_subject,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");
        note_subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        Bundle bundle = getIntent().getExtras();
        subject = bundle.getString("message");
       database db = new database(this);
        note note = db.getNote(subject);
        body.setText(note.getBody());
        note_subject.setText(note.getSubject());

    }
}
