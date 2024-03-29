package com.example.rafae.androidpratica4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class EditTextActivity extends AppCompatActivity {

    EditText input;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        input = (EditText)findViewById(R.id.input);
        sendButton = (Button)findViewById(R.id.button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent i = new Intent(EditTextActivity.this, EditTextDataActivity.class);
                i.putExtra("text", input.getText().toString());
                startActivity(i);
            }
        });
    }
}