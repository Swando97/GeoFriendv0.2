package com.geofriend.geofriend;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LandmarkPopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView landmarkName;
        final TextView landmarkDesc;
        final ImageView landmarkPic;

        setContentView(R.layout.activity_landmark);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final LandmarkMapAdapter lma = new LandmarkMapAdapter();

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int)(width * 0.8), (int)(height*0.7) );

        WindowManager.LayoutParams mLayoutParams = getWindow().getAttributes();
        mLayoutParams.dimAmount = 0.75f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(mLayoutParams);

        final Bundle mapBundle = getIntent().getExtras();

        final String landmarkID = mapBundle.getString("landmarkID");
        //Toast.makeText(LandmarkPopUpActivity.this, "Landmark ID: "+landmarkID, Toast.LENGTH_LONG).show();


        //Instantiating Landmark Description and Image
        landmarkName = findViewById(R.id.landmarkName);
        landmarkDesc = findViewById(R.id.landmarkDescription);
        landmarkPic = findViewById(R.id.landmarkPic);

//        landmarkDesc.setText("Butts");
        landmarkName.setText(lma.landmarks.get(Integer.parseInt(landmarkID)).getName());
        landmarkDesc.setText(lma.landmarks.get(Integer.parseInt(landmarkID)).getDesc());
        landmarkPic.setImageResource(lma.landmarks.get(Integer.parseInt(landmarkID)).getImage());

    }
}
