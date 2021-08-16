package com.example.zad3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");


        ((TextView) findViewById(R.id.textView)).setText(email);


        findViewById(R.id.button).setOnClickListener((v) -> {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        });
    }
}