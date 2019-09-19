package com.example.rafae.praticaandroid1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText input1, input2;
    private TextView result;
    private Button sumButton, subtractionButton, multiplicationButton, divisionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input1 = (EditText)findViewById(R.id.input1);
        input2 = (EditText)findViewById(R.id.input2);
        result = (TextView)findViewById(R.id.resultText);
        sumButton = (Button)findViewById(R.id.sumButton);
        subtractionButton = (Button)findViewById(R.id.subtractionButton);
        multiplicationButton = (Button)findViewById(R.id.multiplicationButton);
        divisionButton = (Button)findViewById(R.id.divisionButton);

        sumButton.setOnClickListener(configureSumButton());
        subtractionButton.setOnClickListener(configureSubtractionButton());
        multiplicationButton.setOnClickListener(configureMultiplicationButton());
        divisionButton.setOnClickListener(configureDivisionButton());

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private View.OnClickListener configureSumButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer val1 = Integer.parseInt(input1.getText().toString());
                Integer val2 = Integer.parseInt(input2.getText().toString());
                Integer sum = val1 + val2;

                result.setText("Sum Result: " + sum.toString());
            }
        };
    }

    private View.OnClickListener configureSubtractionButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer val1 = Integer.parseInt(input1.getText().toString());
                Integer val2 = Integer.parseInt(input2.getText().toString());
                Integer subtraction = val1 - val2;

                result.setText("Subtraction Result: " + subtraction.toString());
            }
        };
    }

    private View.OnClickListener configureMultiplicationButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer val1 = Integer.parseInt(input1.getText().toString());
                Integer val2 = Integer.parseInt(input2.getText().toString());
                Integer multiplication = val1 * val2;

                result.setText("Multiplication Result: " + multiplication.toString());
            }
        };
    }

    private View.OnClickListener configureDivisionButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double val1 = Double.parseDouble(input1.getText().toString());
                Double val2 = Double.parseDouble(input2.getText().toString());

                if (val2 != 0){
                    Double division = val1 / val2;
                    result.setText("Division Result: " + String.format ("%.3f", division));
                } else {
                    result.setText("Error!");
                    Toast.makeText(getApplicationContext(), "Cannot divide by zero!", Toast.LENGTH_LONG).show();
                }

            }
        };
    }
}
