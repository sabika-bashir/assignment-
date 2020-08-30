package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends Activity {
    EditText search;
    Button search_floating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        search=findViewById(R.id.search_edit);
        search_floating=findViewById(R.id.floating_search);
        search_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm=(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getRootView().getWindowToken(),0);
                String city = search.getText().toString();
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);
            }
        });
    }
}
