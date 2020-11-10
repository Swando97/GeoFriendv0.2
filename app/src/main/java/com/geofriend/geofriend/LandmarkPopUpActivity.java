package com.geofriend.geofriend;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LandmarkPopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView landmarkDesc;

        setContentView(R.layout.activity_landmark);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final LandmarkMapAdapter lma = new LandmarkMapAdapter();

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int)(width * 0.8), (int)(height*0.6) );

        WindowManager.LayoutParams mLayoutParams = getWindow().getAttributes();
        mLayoutParams.dimAmount = 0.75f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(mLayoutParams);

        Bundle bundle = getIntent().getExtras();
        final String landmarkID = bundle.getString("landmarkID");
        //Toast.makeText(LandmarkPopUpActivity.this, "Landmark ID: "+landmarkID, Toast.LENGTH_LONG).show();

        landmarkDesc = findViewById(R.id.landmarkDescription);

        landmarkDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                landmarkDesc.setText(lma.landmarks.get(Integer.parseInt(landmarkID)).getDesc());
            }
        });
    }
}
