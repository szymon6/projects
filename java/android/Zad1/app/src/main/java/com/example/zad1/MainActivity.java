package com.example.zad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        findViewById(R.id.button).setOnClickListener((v) -> {
            ((EditText) findViewById(R.id.editTextTextImie)).setText("");
            ((EditText) findViewById(R.id.editTextTextNazwisko)).setText("");
            ((EditText) findViewById(R.id.editTextTextWiek)).setText("");
        });



        findViewById(R.id.button1).setOnClickListener((v) -> {

            String imie = ((EditText) findViewById(R.id.editTextTextImie)).getText().toString();
            String nazwisko = ((EditText) findViewById(R.id.editTextTextNazwisko)).getText().toString();

            int wiek=0;
            try {
                wiek= Integer.parseInt(((EditText) findViewById(R.id.editTextTextWiek)).getText().toString());
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), "zły format", Toast.LENGTH_LONG).show();
                return;
            }

            String plec="";
            try {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int selectedId= radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                plec= radioButton.getText().toString();
            }
            catch  (Exception e){
                Toast.makeText(getApplicationContext(), "podaj plec", Toast.LENGTH_LONG).show();
                return;
            }





            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("imie",imie);
            intent.putExtra("nazwisko",nazwisko);
            intent.putExtra("wiek",wiek);
            intent.putExtra("plec",plec);
            startActivity(intent);
        });

    }
}