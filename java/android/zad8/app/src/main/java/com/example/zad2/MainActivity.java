package com.example.zad2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private List<String> marki = new ArrayList<>();
    private List<String> daty = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listView);
        generate();
        listView.setAdapter(new AutoAdapter(this, R.layout.list_item, marki));



    }



    private void generate() {
        for(int i = 0; i < 30; i++) {
            marki.add("marka " + i);
            daty.add(String.valueOf(1990+new Random().nextInt(30)));
        }
    }






    private class AutoAdapter extends ArrayAdapter<String> {
        private int layout;

        private AutoAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);

            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Auto auto = null;

            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                Auto rowView = new Auto();


                rowView.marka = (TextView) convertView.findViewById(R.id.textViewMarka);
                rowView.rok = (TextView) convertView.findViewById(R.id.textViewRok);
                rowView.button = (Button) convertView.findViewById(R.id.button);

                convertView.setTag(rowView);
            }

            auto = (Auto) convertView.getTag();
            auto.button.setOnClickListener(v ->{
                        Toast.makeText(getContext(), marki.get(position), Toast.LENGTH_SHORT).show();
                    });
            auto.marka.setText(marki.get(position));
            auto.rok.setText(daty.get(position));

            return convertView;
        }
    }
    private class Auto {

        TextView  marka;
        TextView rok;
        Button button;

    }










}

