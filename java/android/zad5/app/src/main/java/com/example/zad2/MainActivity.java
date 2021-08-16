package com.example.zad2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.colors,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                ((TextView) findViewById(R.id.textView)).setText(text);

                changeColor(text);
                System.out.println(text);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setActivityBackgroundColor(Color.BLUE);
    }

    void changeColor(String color){
        switch(color) {
            case "Niebieski":
                setActivityBackgroundColor(Color.BLUE);
                break;
            case "Czerwony":
                setActivityBackgroundColor(Color.RED);
                break;
            case "Zielony":
                setActivityBackgroundColor(Color.GREEN);
                break;

        }
    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}