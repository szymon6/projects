package com.example.zad2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {


    int firstNumber;
    int secondNumber;

    int stan = 0;

    char operation;

    EditText editText;

    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        findViewById(R.id.button1).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button2).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button3).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button4).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button5).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button6).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button7).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button8).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button9).setOnClickListener(this::NumberButtonClicked);
        findViewById(R.id.button0).setOnClickListener(this::NumberButtonClicked);

        editText = findViewById(R.id.editTextTextPersonName);



        findViewById(R.id.buttonPlus).setOnClickListener(this::OperationNumberClicked);
        findViewById(R.id.buttonMinus).setOnClickListener(this::OperationNumberClicked);
        findViewById(R.id.buttonMultiply).setOnClickListener(this::OperationNumberClicked);
        findViewById(R.id.buttonDivide).setOnClickListener(this::OperationNumberClicked);


        findViewById(R.id.buttonEnter).setOnClickListener(this::Calculate);

    }

    private void Calculate(View view) {
        switch(operation) {
            case '/':
                result=firstNumber/(double)secondNumber;
                break;
            case '*':
                result=firstNumber*secondNumber;
                break;
            case '-':
                result=firstNumber-secondNumber;
                break;
            case '+':
                result=firstNumber+secondNumber;
                break;
            default:

        }

        editText.setText(String.valueOf(result));
        stan=0;
    }

    void NumberButtonClicked(View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        int number = Integer.parseInt(buttonText);
        System.out.println(number);

        setNumber(number);
    }

    void OperationNumberClicked(View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        operation = buttonText.charAt(0);
    }

    void setNumber(int number) {
        if (stan == 0) {
            firstNumber = number;
            stan=1;
        }
        else if (stan == 1)
            secondNumber = number;

        editText.setText(String.valueOf(number));

    }







    
}

