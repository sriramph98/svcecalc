package com.cosmos.android.svcecalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cosmos.android.svcecalculator.view.CustomEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    TextView headerTextView = null;
    Button submitButton = null;


    CustomEditText cat1 = null;
    CustomEditText ass1 = null;
    CustomEditText cat2 = null;
    CustomEditText ass2 = null;
    CustomEditText cat3 = null;
    CustomEditText ass3 = null;


    private void initializeEditTexts(){
        cat1 = findViewById(R.id.catEditText1);
        ass1 = findViewById(R.id.assEditText1);
        cat2 = findViewById(R.id.catEditText2);
        ass2 = findViewById(R.id.assEditText2);
        cat3 = findViewById(R.id.catEditText3);
        ass3 = findViewById(R.id.assEditText3);
    }
    private void initialize(){
        initializeEditTexts();
        headerTextView = findViewById(R.id.headerText);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private double calculatePhaseMark(Editable cat_mark_s, Editable ass_mark_s){
        double cat_mark = Double.parseDouble(cat_mark_s.toString());
        double ass_mark = Double.parseDouble(ass_mark_s.toString());
        return ((cat_mark / 50 * 0.7) + (ass_mark / 50 * 0.3)) + (50/3);
    }
    private boolean validateEditText() {
        return true;
    }

    private double addValues() {
        return calculatePhaseMark(cat1.getText(), ass1.getText()) + calculatePhaseMark(cat2.getText(), ass2.getText()) + calculatePhaseMark(cat3.getText(), ass3.getText());
    }

    @Override
    public void onClick(View v) {
        //validate input
        if(validateEditText()) {
            double internalMark = addValues();
            Intent intent = new Intent();
            intent.setClass(context, ShowResult.class);
            intent.putExtra("internals", internalMark);
            startActivity(intent);
        }
    }


}
