package com.cosmos.android.svcecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView headerTextView = null;
    Button submitButton = null;
    ConstraintLayout constraintLayout = null;

    private void initialize(){
        headerTextView = findViewById(R.id.headerText);
        submitButton = findViewById(R.id.submitButton);
        constraintLayout = findViewById(R.id.constraintLayout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }
}
