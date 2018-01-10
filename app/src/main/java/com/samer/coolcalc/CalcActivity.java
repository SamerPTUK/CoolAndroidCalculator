package com.samer.coolcalc;

import android.app.Activity;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends Activity {

    Button zero, one, tow, three, four, five, six, seven, eight, nine, clear;
    ImageButton divide, multiply, subtract, add, calculate;
    TextView resultTV, titleTV;

    String runningNumber = "", leftValue = "", rightValue = "", allQuation = "";

    public enum  Operation {
        ADD, SUBTRACT, DIVIDE, MULTIPLY, EQUAL
    }

    Operation currentOperation = null;

    double calculateResult = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        tow = findViewById(R.id.tow);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        clear = findViewById(R.id.clear);

        divide = findViewById(R.id.divideB);
        multiply = findViewById(R.id.multiplyB);
        subtract = findViewById(R.id.subtractB);
        add = findViewById(R.id.addB);
        calculate = findViewById(R.id.calculateB);

        resultTV = findViewById(R.id.resultTV);
        titleTV = findViewById(R.id.titleTV);

        resultTV.setText("0.0");


        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("1");
            }
        });

        tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPressed("9");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runningNumber = "";
                leftValue = "";
                rightValue = "";
                calculateResult = 0;
                currentOperation = null;
                resultTV.setText("0.0");
                titleTV.setText("Calculator");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.DIVIDE);
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.MULTIPLY);
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.SUBTRACT);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOperation(Operation.ADD);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(runningNumber.isEmpty() && currentOperation == null) {
                    Toast.makeText(getApplicationContext(), "Please enter your problem", Toast.LENGTH_LONG).show();
                } else if(currentOperation == null) {
                    Toast.makeText(getApplicationContext(), "Please enter the operation and the second number", Toast.LENGTH_LONG).show();
                } else if(runningNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter the second number", Toast.LENGTH_LONG).show();
                } else {
                    processOperation(Operation.EQUAL);
                }
            }
        });


    } // end onCreate



    private void numberPressed(String number) {
        runningNumber += number;
        resultTV.setText(runningNumber);
    }


    private String getOperation(Operation ope) {
        String operation = "";
        switch (ope) {
            case ADD:
                operation = "+";
            break;
            case SUBTRACT:
                operation = "-";
            break;
            case MULTIPLY:
                operation = "*";
            break;
            case DIVIDE:
                operation = "/";
            break;
        }
        return  operation;
    }

    private void processOperation(Operation ope) {
        if(currentOperation == Operation.EQUAL && !leftValue.isEmpty() && runningNumber == "") {
            currentOperation = ope;
            allQuation += getOperation(currentOperation);
            titleTV.setText(allQuation);
        } else if(currentOperation == null) {
            leftValue = runningNumber;
            currentOperation = ope;

            allQuation += leftValue;
            allQuation += getOperation(currentOperation);
            titleTV.setText(allQuation);

            runningNumber = "";
        } else if(!runningNumber.isEmpty()) {
            rightValue = runningNumber;

            switch (currentOperation) {
                case ADD:
                    calculateResult = Double.valueOf(leftValue) + Double.valueOf(rightValue);
                    break;
                case SUBTRACT:
                    calculateResult = Double.valueOf(leftValue) - Double.valueOf(rightValue);
                    break;
                case MULTIPLY:
                    calculateResult = Double.valueOf(leftValue) * Double.valueOf(rightValue);
                    break;
                case DIVIDE:
                    if(!rightValue.equals("0"))
                        calculateResult = Double.valueOf(leftValue) / Double.valueOf(rightValue);
                    else
                        Toast.makeText(getApplicationContext(), "Cannot divide on Zero!", Toast.LENGTH_LONG).show();
                    break;
            } // end switch

            leftValue = String.valueOf(calculateResult);
            currentOperation = ope;
            resultTV.setText(leftValue);
            runningNumber = "";

            if(currentOperation == Operation.EQUAL)
                allQuation += rightValue;
            else
                allQuation += rightValue += getOperation(currentOperation);

            titleTV.setText(allQuation);
        }
        else {
            Toast.makeText(getApplicationContext(), "You can select only one operation in the same time!", Toast.LENGTH_LONG).show();
        }
    } // end processOperation



}
