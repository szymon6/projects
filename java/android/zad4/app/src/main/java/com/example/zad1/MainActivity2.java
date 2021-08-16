package com.example.zad1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        String imie = getIntent().getStringExtra("imie");
        String nazwisko = getIntent().getStringExtra("nazwisko");
        int wiek = getIntent().getIntExtra("wiek", 0);
        String plec = getIntent().getStringExtra("plec");
        boolean zgoda = getIntent().getBooleanExtra("zgoda",false);



        ((TextView) findViewById(R.id.textViewImie)).setText(imie);
        ((TextView) findViewById(R.id.textViewNazwisko)).setText(nazwisko);
        ((TextView) findViewById(R.id.textViewPlec)).setText(plec);
        ((TextView) findViewById(R.id.textViewWiek)).setText(String.valueOf(wiek));
        ((TextView) findViewById(R.id.textViewZgoda)).setText(zgoda ? "udzielona":"nie udzielona");

    }
}