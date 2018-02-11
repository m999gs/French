package com.example.mohitgarg.french;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
public class translate extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate);
        EditText eng=(EditText) findViewById(R.id.EngText);
        TextView fren=(TextView) findViewById(R.id.FrenText);
    }
}