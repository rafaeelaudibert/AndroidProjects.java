package com.example.rafae.praticaandroid2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    TextView textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        textField = (TextView)findViewById(R.id.textField);
        Intent intent = getIntent();
        String value = intent.getStringExtra("text");

        textField.setText(value);
    }
}
