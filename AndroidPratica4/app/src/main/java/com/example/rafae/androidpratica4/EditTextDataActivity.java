package com.example.rafae.androidpratica4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EditTextDataActivity extends AppCompatActivity {

    TextView textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_data);

        textField = (TextView)findViewById(R.id.textField);
        Intent intent = getIntent();
        String value = intent.getStringExtra("text");

        textField.setText(value);
    }
}
