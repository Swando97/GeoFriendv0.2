package com.geofriend.geofriendapplicationdevs;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    TextView launch_title, terms_of_use;
    Button accept_button;

    ProminentDisclosureDialogFragment pddf = new ProminentDisclosureDialogFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        launch_title = findViewById(R.id.launchTitle);
        terms_of_use = findViewById(R.id.termsOfUse);
        accept_button = (Button) findViewById(R.id.acceptButton);

        pddf.show(getSupportFragmentManager(),"PDDF");

        terms_of_use.setMovementMethod(new ScrollingMovementMethod());

        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}