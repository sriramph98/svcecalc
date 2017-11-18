package com.cosmos.android.svcecalculator.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import com.cosmos.android.svcecalculator.R;

/**
 * Created by Home on 19-11-2017.
 */
public class CustomEditText extends AppCompatEditText {

    AppCompatEditText editText = this;

    public CustomEditText(Context context) {
        super(context);
        addTextChangedListener(new CustomTextWatcher());
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(new CustomTextWatcher());
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
            if(TextUtils.isEmpty(s)){
                editText.setError("Input invalid", getResources().getDrawable(R.drawable.warning_icon));
            }
        }
    }

}
