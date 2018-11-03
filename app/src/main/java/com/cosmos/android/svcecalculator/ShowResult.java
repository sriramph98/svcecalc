package com.cosmos.android.svcecalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cosmos.android.svcecalculator.model.Phase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.content.Intent.ACTION_SEND;


public class ShowResult extends AppCompatActivity {

    TextView txt = null;
    Button minBtn = null;
    Context context = this;
    LinearLayout minimumGradeContainer = null;
    LinearLayout minimumGradeContainer2 = null;
    LinearLayout phaseContainer = null;
    ScrollView resultScrollView = null;
    ImageButton screenshotBtn = null;
    ImageButton backBtn = null;
    LinearLayout showResultHeader = null;

    private void initialize() {
        txt = findViewById(R.id.resultTextView);
        minimumGradeContainer = findViewById(R.id.minimumGradeContainer);
        minimumGradeContainer2 = findViewById(R.id.minimumGradeContainer2);
        phaseContainer = findViewById(R.id.phaseContainer);
        resultScrollView = findViewById(R.id.resultScrollView);
        screenshotBtn = findViewById(R.id.screenshotBtn);
        backBtn = findViewById(R.id.backBtn);
        showResultHeader = findViewById(R.id.showResultHeader);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void addMinimumGradeView(Set<Map.Entry<String, Double>> entrySet, ViewGroup root) {

        for (Map.Entry<String, Double> value : entrySet) {
            double minGradeVal = ((value.getValue() - DataHelper.INSTANCE.getInternatlMark()) * 2.0d);
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.minimum_grade_item, root, false);
            TextView valueTxt = (viewGroup.findViewById(R.id.minimumPoints));
            TextView gradeTxt = (viewGroup.findViewById(R.id.minimumLabel));

            gradeTxt.setText(value.getKey());
            valueTxt.setText(String.format("%1$,.0f", minGradeVal));

            if (minGradeVal > 100d) {
                valueTxt.setText(":(");
                valueTxt.setTextColor(ContextCompat.getColor(this, R.color.error_light));

            } else if (minGradeVal < 45) {
                valueTxt.setText("45");
            }
            root.addView(viewGroup);
        }
    }

    private Bitmap getBitmapFromView(View view) {
        ScrollView scrollView = (ScrollView) view;
        int width = scrollView.getChildAt(0).getWidth();
        int height = scrollView.getChildAt(0).getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }


    Uri getImageUri(Bitmap b) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Title", "");
        return Uri.parse(path);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SoinSansNeue-Light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
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
        addMinimumGradeView(values.entrySet(), minimumGradeContainer);
        values.clear();
        values.put("C", 60.0d);
        values.put("D", 57.0d);
        values.put("E", 50.0d);
        addMinimumGradeView(values.entrySet(), minimumGradeContainer2);

        backBtn.setOnClickListener(v -> finish());

        screenshotBtn.setOnClickListener(v -> {
            Intent shareIntent = new Intent(ACTION_SEND);
            showResultHeader.setVisibility(View.GONE);
            shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(getBitmapFromView(resultScrollView)));
            showResultHeader.setVisibility(View.VISIBLE);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.share_text)));
        });
    }

}
