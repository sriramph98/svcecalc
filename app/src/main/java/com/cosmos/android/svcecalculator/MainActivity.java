package com.cosmos.android.svcecalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    TextView headerTextView = null;
    Button submitButton = null;
    ConstraintLayout constraintLayout = null;

    private void initialize(){
        headerTextView = findViewById(R.id.headerText);
        submitButton = findViewById(R.id.submitButton);
        constraintLayout = findViewById(R.id.constraintLayout);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, ShowResult.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }
}
