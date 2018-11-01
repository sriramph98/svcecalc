package com.cosmos.android.svcecalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cosmos.android.svcecalculator.model.Phase;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class ShowResult extends AppCompatActivity {

    TextView txt = null;
    Button minBtn = null;
    Context context = this;
    LinearLayout minimumGradeContainer = null;
    LinearLayout phaseContainer = null;

    private void initialize() {
        txt = findViewById(R.id.resultTextView);
        minimumGradeContainer = findViewById(R.id.minimumGradeContainer);
        phaseContainer = findViewById(R.id.phaseContainer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        initialize();
        String internals_s = String.format("%1$,.2f", DataHelper.INSTANCE.getInternatlMark());
        txt.setText(internals_s);

        int i = 0;
        for (Phase phase : DataHelper.INSTANCE.getPhases()) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.phase_item, phaseContainer, false);
            TextView catMarkTxt = viewGroup.findViewById(R.id.catMark);
            TextView assMarkTxt = viewGroup.findViewById(R.id.assMark);
            TextView phaseTitle = (TextView) LayoutInflater.from(this).inflate(R.layout.phase_text_view, phaseContainer, false);
            phaseTitle.setText("Phase " + ++i);
            catMarkTxt.setText(String.valueOf(phase.getCatMark()));
            assMarkTxt.setText(String.valueOf(phase.getAssMark()));
            phaseContainer.addView(phaseTitle);
            phaseContainer.addView(viewGroup);
        }

        Map<String, Double> values = new LinkedHashMap<>();
        values.put("S", 90.0d);
        values.put("A", 80.0d);
        values.put("B", 70.0d);
        values.put("C", 60.0d);
        values.put("D", 57.0d);
        values.put("E", 50.0d);

        for (Map.Entry<String, Double> value : values.entrySet()) {
            double minGradeVal = ((value.getValue() - DataHelper.INSTANCE.getInternatlMark()) * 2.0d);
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_minimum_grade, minimumGradeContainer, false);
            TextView valueTxt = (viewGroup.findViewById(R.id.minimumPoints));
            TextView gradeTxt = (viewGroup.findViewById(R.id.minimumLabel));

            gradeTxt.setText(value.getKey());
            valueTxt.setText(String.format("%1$,.0f", minGradeVal));

            if (minGradeVal > 100d) {
                valueTxt.setText(":(");

            } else if (minGradeVal < 45) {
                valueTxt.setText("45");
            }
            minimumGradeContainer.addView(viewGroup);
        }
    }

}
