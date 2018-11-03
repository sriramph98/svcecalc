package com.cosmos.android.svcecalculator.view;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmos.android.svcecalculator.R;

/**
 * Created by Sriram on 19-11-2017.
 */
public class NumberEditText extends TextInputEditText {
    private final String TAG = "NumberEditText";

    public double getDouble() {
        double i;
        try {
            i = Double.parseDouble(getText().toString());
        } catch (NumberFormatException nfe) {
            i = 0;
            Log.i(TAG, nfe.getMessage());
        }
        return i;
    }

    void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public NumberEditText(Context context) {
        super(context);
        init();
        addTextChangedListener(new CustomTextWatcher());
    }

    public NumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        addTextChangedListener(new CustomTextWatcher());
    }

    public NumberEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        addTextChangedListener(new CustomTextWatcher());
    }

    class CustomTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s)) {
                setError("Field is empty\nPlease enter the mark");
            }
            try {
                if (Double.parseDouble(s.toString()) > 50) {
                    setError("Invalid input");
                }

                if (s.toString().contains(".")) {
                    setText("0.");
                    setSelection(getText().length());
                }
            } catch (NumberFormatException ne) {
                Log.e("DOUBLE", ne.getMessage());
            }
        }
    }
}
