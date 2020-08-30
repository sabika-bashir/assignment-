package com.example.note;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editNote extends AppCompatActivity {
    String idnote;
    EditText subjectedit, bodyedit;
    database db = new database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Note");
        subjectedit = findViewById(R.id.editsubject);
        bodyedit = findViewById(R.id.editbody);
        Bundle bundle = getIntent().getExtras();
        idnote = bundle.getString("message");
        note note = db.getNote(idnote);
        final String subject = note.getSubject();
        String body = note.getBody();
        subjectedit.setText(subject);
        bodyedit.setText(body);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long ids=5;
        if(item.getItemId() == R.id.editclass){
            note note = new note(ids,subjectedit.getText().toString(),bodyedit.getText().toString());
            Log.d("EDITED", "edited: before saving id -> " + note.getID());
            long id = db.editNote(note);
            Log.d("EDITED", "EDIT: id " + id);
            Toast.makeText(this, "Note Edited.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
