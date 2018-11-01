package com.cosmos.android.svcecalculator;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cosmos.android.svcecalculator.model.Phase;
import com.cosmos.android.svcecalculator.view.NumberEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    TextView headerTextView = null;
    Button submitButton = null;


    NumberEditText cat1 = null;
    NumberEditText ass1 = null;
    NumberEditText cat2 = null;
    NumberEditText ass2 = null;
    NumberEditText cat3 = null;
    NumberEditText ass3 = null;

    boolean isValidate1 = true;
    boolean isValidate2 = true;
    boolean isValidate3 = true;


    private void initializeEditTexts() {
        cat1 = findViewById(R.id.catEditText1);
        ass1 = findViewById(R.id.assEditText1);
        cat2 = findViewById(R.id.catEditText2);
        ass2 = findViewById(R.id.assEditText2);
        cat3 = findViewById(R.id.catEditText3);
        ass3 = findViewById(R.id.assEditText3);

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verification process
                NumberEditText editText = (NumberEditText) v;
                if (editText.getDouble() > 50 && editText.getDouble() == 0)
                    isValidate1 = false;
            }
        };

        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verification process
                NumberEditText editText = (NumberEditText) v;
                if (editText.getDouble() > 50 && editText.getDouble() == 0)
                    isValidate2 = false;
            }
        };

        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verification process
                NumberEditText editText = (NumberEditText) v;
                if (editText.getDouble() > 50 && editText.getDouble() == 0)
                    isValidate3 = false;
            }
        };

        cat1.verifyCallback(listener1);
        ass1.verifyCallback(listener1);
        cat2.verifyCallback(listener2);
        ass2.verifyCallback(listener2);
        cat3.verifyCallback(listener3);
        ass3.verifyCallback(listener3);


    }

    private void initialize() {
        headerTextView = findViewById(R.id.headerText);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        initializeEditTexts();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    @Override
    public void onClick(View v) {
        cat1.verify();
        ass1.verify();
        cat2.verify();
        ass2.verify();
        cat3.verify();
        ass3.verify();
        //validate input
        if (isValidate1 || isValidate2 || isValidate3) {
            Intent intent = new Intent();
            intent.setClass(context, ShowResult.class);
            DataHelper.INSTANCE.getPhases().clear();
            DataHelper.INSTANCE.getPhases().add(new Phase(cat1.getDouble(), ass1.getDouble()));
            DataHelper.INSTANCE.getPhases().add(new Phase(cat2.getDouble(), ass2.getDouble()));
            DataHelper.INSTANCE.getPhases().add(new Phase(cat3.getDouble(), ass3.getDouble()));
            startActivity(intent);
        }
        isValidate1 = true;
        isValidate2 = true;
        isValidate3 = true;
    }


}
