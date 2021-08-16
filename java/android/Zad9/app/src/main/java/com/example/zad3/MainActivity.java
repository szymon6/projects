package com.example.zad3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<String> nazwy = Arrays.asList("Polska","Niemcy","Francja","Włochy");
    private List<Integer> flagi = Arrays.asList(R.drawable.poland,R.drawable.germany,R.drawable.france,R.drawable.italy);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new AutoAdapter(this, R.layout.list_item, nazwy));



    }









    private class AutoAdapter extends ArrayAdapter<String> {
        private int layout;

        private AutoAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);

            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Kraj kraj = null;

            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                Kraj rowView = new Kraj();


                rowView.nazwa = (TextView) convertView.findViewById(R.id.textViewMarka);
                rowView.flaga = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(rowView);
            }


            kraj = (Kraj) convertView.getTag();

            kraj.nazwa.setText(nazwy.get(position));
            kraj.flaga.setImageResource(flagi.get(position));


            return convertView;
        }
    }
    private class Kraj {

        TextView nazwa;
        ImageView flaga;


    }










}
