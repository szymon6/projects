package com.example.zad1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button1).setOnClickListener((v) -> {

            String imie = ((EditText) findViewById(R.id.editTextTextImie)).getText().toString();
            String nazwisko = ((EditText) findViewById(R.id.editTextTextNazwisko)).getText().toString();

            int wiek = 0;
            try {
                wiek = Integer.parseInt(((EditText) findViewById(R.id.editTextTextWiek)).getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "zły format", Toast.LENGTH_LONG).show();
                return;
            }

            String plec = "";
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            try {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                plec = radioButton.getText().toString();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "podaj plec", Toast.LENGTH_LONG).show();
                return;
            }

            boolean zgoda = ((CheckBox) findViewById(R.id.checkBox)).isChecked();

            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("imie", imie);
            intent.putExtra("nazwisko", nazwisko);
            intent.putExtra("wiek", wiek);
            intent.putExtra("plec", plec);
            intent.putExtra("zgoda", zgoda);
            startActivity(intent);
        });


    }


    public void onRadioButtonClicked(View view) {
        Toast.makeText(getApplicationContext(), view.getId()==R.id.radioButtonM?"Mężczyzna":"Kobieta", Toast.LENGTH_SHORT).show();
    }
}