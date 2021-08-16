package com.example.zad3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView[] textViews = new TextView[7];
    static String[] emails = new String[7];
    static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViews[0] = ((TextView) findViewById(R.id.textView));
        textViews[1] = ((TextView) findViewById(R.id.textView2));
        textViews[2] = ((TextView) findViewById(R.id.textView3));
        textViews[3] = ((TextView) findViewById(R.id.textView4));
        textViews[4] = ((TextView) findViewById(R.id.textView5));
        textViews[5] = ((TextView) findViewById(R.id.textView6));
        textViews[6] = ((TextView) findViewById(R.id.textView7));


        findViewById(R.id.button).setOnClickListener((v) -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        });

        for (int i = 0; i < 7; i++) {
            String email = emails[i];
            textViews[i].setOnClickListener((v) -> {
                Intent intent = new Intent(this, MainActivity3.class);
                intent.putExtra("email",email);
                startActivity(intent);
            });
        }


        addNewIfSet();
        show();
    }

    private void show() {
        for (int i = 0; i < 7; i++) {

            String s = emails[i];
            if(s!=null&&s.length()>3)
                s=s.substring(0,3)+"...";

            textViews[i].setText(s);
        }
    }

    private void addNewIfSet() {

        Intent intent = getIntent();

        if (intent.hasExtra("email")) {
            addNew(intent.getStringExtra("email"));
        }
    }


    void addNew(String s) {
        emails[index++]=s;
    }

}