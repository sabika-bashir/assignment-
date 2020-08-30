package com.example.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addNote extends AppCompatActivity {
         EditText subject ,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Note");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.del_save_option,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete) {

            Toast.makeText(this, "Deleted" , Toast.LENGTH_SHORT).show();
            onBackPressed();

        }
        if(item.getItemId() == R.id.save) {
               note note = new note(subject.getText().toString(),body.getText().toString());
   database db = new database(this);
   db.addNote(note);
            Toast.makeText(this, " Note Saved" , Toast.LENGTH_SHORT).show();
            goBack();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goBack() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
