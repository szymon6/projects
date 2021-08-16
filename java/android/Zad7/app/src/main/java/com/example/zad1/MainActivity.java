package com.example.zad1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        EditText editText = ((EditText) findViewById(R.id.editText));

        List<String> list = new ArrayList<>();


        findViewById(R.id.button).setOnClickListener((v) -> {


            list.add(editText.getText().toString());
            editText.setText("");


            ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

            listView.setAdapter(adapter);

        });


        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selectedFromList = (String) (listView.getItemAtPosition(position));
            Toast.makeText(getApplicationContext(), selectedFromList, Toast.LENGTH_SHORT).show();
        });


    }
}