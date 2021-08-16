package com.example.zad3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Czy chcesz wysłać?");
        alertDialog.setPositiveButton("tak", (dialog, which) -> {
            setActivityBackgroundColor(Color.GREEN);
        });
        alertDialog.setNegativeButton("nie", (dialog, which) -> {
            setActivityBackgroundColor(Color.RED);
        });

        alertDialog.create();

        findViewById(R.id.button).setOnClickListener((v) -> {
            String adres = ((EditText) findViewById(R.id.editTextAdres)).getText().toString();
            String tresc = ((EditText) findViewById(R.id.editTextTresc)).getText().toString();

            alertDialog.show();

        });


    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}