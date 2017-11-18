package com.cosmos.android.svcecalculator;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class ShowResult extends AppCompatActivity {

    TextView txt = null;

    private void initialize() {
        txt = findViewById(R.id.resultTextView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        initialize();
        double internals = getIntent().getDoubleExtra("internals",  -1);
        String internals_s = String.format("%1$,.2f", internals);
        txt.setText(internals_s);
    }

}
