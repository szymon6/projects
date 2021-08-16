package com.example.zad3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch mySwitch = findViewById(R.id.switch1);
        View screenView = findViewById(R.id.view);
        TextView tw = findViewById(R.id.textView);

        screenView.setBackgroundColor(Color.RED);

        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                tw.setText("wyłącz");
                screenView.setBackgroundColor(Color.BLUE);

            } else {

                tw.setText("włącz");
                screenView.setBackgroundColor(Color.RED);
            }

        });
    }
}