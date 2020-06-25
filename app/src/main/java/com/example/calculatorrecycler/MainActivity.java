package com.example.calculatorrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculatorrecycler.R;

public class MainActivity extends AppCompatActivity {
    TextView panel;
    private double result, firstNumber, secondNumber;
    private String operation;
    String intermediateNumber = "";
    boolean isOperation = false;

    Double res;
    Button save_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        panel = findViewById(R.id.panel);

        save_result = findViewById(R.id.button_save);

        findViewById(R.id.button_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0.0;
                firstNumber = 0.0;
                secondNumber = 0.0;
                operation = null;
                intermediateNumber = "";
                isOperation = false;
                panel.setText("");
            }
        });

        save_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("Result", res);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }




    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result = savedInstanceState.getDouble("result");
        firstNumber = savedInstanceState.getDouble("firstNumber");
        secondNumber = savedInstanceState.getDouble("secondNumber");
        operation = savedInstanceState.getString("operation");
        intermediateNumber = savedInstanceState.getString("intermediateNumber");
        isOperation = savedInstanceState.getBoolean("isOperation");

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("result", result);
        outState.putDouble("firstNumber", firstNumber);
        outState.putDouble("secondNumber", secondNumber);
        outState.putString("operation", operation);
        outState.putString("intermediateNumber", intermediateNumber);
        outState.putBoolean("isOperation", isOperation);


    }

    public void onClick_buttonTyping(View view) {
        if (isOperation) {
            panel.setText("");
            result = 0;
            operation = null;
            secondNumber = 0;
            firstNumber = 0;
            intermediateNumber = "";
            isOperation = false;
        }

        switch (view.getId()) {
            case R.id.button0:
                intermediateNumber += "0";
                break;
            case R.id.button1:
                intermediateNumber += "1";
                break;

            case R.id.button2:
                intermediateNumber += "2";
                break;

            case R.id.button3:
                intermediateNumber += "3";
                break;
            case R.id.button4:
                intermediateNumber += "4";
                break;

            case R.id.button5:
                intermediateNumber += "5";
                break;

            case R.id.button6:
                intermediateNumber += "6";
                break;

            case R.id.button7:
                intermediateNumber += "7";
                break;

            case R.id.button8:
                intermediateNumber += "8";
                break;

            case R.id.button9:
                intermediateNumber += "9";
                break;

            case R.id.buttonDot:
                intermediateNumber += ".";
                break;
        }
        panel.setText(intermediateNumber);
    }

    public void onClick_buttonLogic(View view) {
        switch (view.getId()) {
            case R.id.buttonAddition:
                firstNumber = Double.parseDouble(intermediateNumber);
                operation = "+";
                intermediateNumber = "";
                panel.setText("+");
                break;
            case R.id.buttonMinus:
                firstNumber = Double.parseDouble(intermediateNumber);
                operation = "-";
                intermediateNumber = "";
                panel.setText("-");
                break;
            case R.id.buttonMult:
                firstNumber = Double.parseDouble(intermediateNumber);
                operation = "*";
                intermediateNumber = "";
                panel.setText("*");
                break;
            case R.id.buttonDivision:
                firstNumber = Double.parseDouble(intermediateNumber);
                operation = "/";
                intermediateNumber = "";
                panel.setText("/");
                break;
            case R.id.buttonEquals:
                secondNumber = Double.parseDouble(intermediateNumber);

                switch (operation) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        result = firstNumber / secondNumber;
                        break;
                }

                res = result;
                panel.setText(result + "");
                isOperation = true;
                break;
        }

    }
}
