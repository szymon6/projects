package com.example.zad3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.button).setOnClickListener((v) -> {
            String email =((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("email",email);
            startActivity(intent);
        });

    }
}