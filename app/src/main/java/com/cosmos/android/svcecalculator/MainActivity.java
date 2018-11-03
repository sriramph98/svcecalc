package com.cosmos.android.svcecalculator;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmos.android.svcecalculator.model.Phase;
import com.cosmos.android.svcecalculator.view.NumberEditText;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppActivity implements View.OnClickListener {

    Context context = this;
    TextView headerTextView = null;
    Button submitButton = null;
    Button resetButton = null;

    ArrayList<NumberEditText> editTexts = new ArrayList<>();

    @SuppressLint("FindViewByIdCast")
    private void initializeEditTexts() {
        editTexts.add(findViewById(R.id.catEditText1));
        editTexts.add(findViewById(R.id.catEditText2));
        editTexts.add(findViewById(R.id.catEditText3));
        editTexts.add(findViewById(R.id.assEditText1));
        editTexts.add(findViewById(R.id.assEditText2));
        editTexts.add(findViewById(R.id.assEditText3));
    }

    private void initialize() {
        headerTextView = findViewById(R.id.headerText);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);
        initializeEditTexts();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SoinSansNeue-Roman.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        WRITE_EXT_REQUEST_CODE);
            }

        } else {
            // user already provided permission
            // perform function for what you want to achieve
        }
        initialize();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                Intent intent = new Intent();
                intent.setClass(context, ShowResult.class);
                DataHelper.INSTANCE.getPhases().clear();
                DataHelper.INSTANCE.getPhases().add(new Phase(editTexts.get(0).getDouble(), editTexts.get(3).getDouble()));
                DataHelper.INSTANCE.getPhases().add(new Phase(editTexts.get(1).getDouble(), editTexts.get(4).getDouble()));
                DataHelper.INSTANCE.getPhases().add(new Phase(editTexts.get(2).getDouble(), editTexts.get(5).getDouble()));

                for (Phase phase : DataHelper.INSTANCE.getPhases()) {
                    if (phase.exceeds()) {
                        Toast.makeText(this, "Invalid input, try with different inputs", Toast.LENGTH_LONG).show();
                        DataHelper.INSTANCE.getPhases().clear();
                        return;
                    }
                }
                startActivity(intent);
                break;
            case R.id.resetButton:
                for (NumberEditText ndt : editTexts) {
                    ndt.setText("");
                }
                break;
        }
    }
}
