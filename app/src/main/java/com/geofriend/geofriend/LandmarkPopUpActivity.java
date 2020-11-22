package com.geofriend.geofriend;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LandmarkPopUpActivity extends AppCompatActivity {

    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TextView landmarkName;
        final TextView landmarkDesc;
        final ImageView landmarkPic;
        final Button visitButton;

        setContentView(R.layout.activity_landmark);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final LandmarkMapAdapter lma = new LandmarkMapAdapter();

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int)(width * 0.8), (int)(height*0.65) );

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
        landmarkDesc.setMovementMethod(new ScrollingMovementMethod());

        landmarkPic = findViewById(R.id.landmarkPic);
        visitButton = (Button) findViewById(R.id.visit_button);

//        landmarkDesc.setText("Butts");
        landmarkName.setText(lma.ulandmarks.get(Integer.parseInt(landmarkID)).getName());
        landmarkDesc.setText(lma.ulandmarks.get(Integer.parseInt(landmarkID)).getDesc());
        landmarkPic.setImageResource(lma.ulandmarks.get(Integer.parseInt(landmarkID)).getImage());

        visitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lma.ulandmarks.get(Integer.parseInt(landmarkID)).isVisited() == false) {
                    Toast.makeText(LandmarkPopUpActivity.this, "Visited Landmark: " + landmarkID, Toast.LENGTH_LONG).show();
                    lma.ulandmarks.set(Integer.parseInt(landmarkID), lma.landmarks.get(Integer.parseInt(landmarkID)));

                    landmarkName.setText(lma.ulandmarks.get(Integer.parseInt(landmarkID)).getName());
                    landmarkDesc.setText(lma.ulandmarks.get(Integer.parseInt(landmarkID)).getDesc());

                    databaseConnection.updateUserData(databaseConnection.getUserID());
                }
                else{
                    Toast.makeText(LandmarkPopUpActivity.this, "Landmark " + landmarkID + " has already been visited.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
