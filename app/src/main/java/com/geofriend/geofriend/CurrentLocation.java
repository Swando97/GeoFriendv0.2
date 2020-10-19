package com.geofriend.geofriend;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class CurrentLocation extends AppCompatActivity {

    private Button btn;
    private TextView textView;
    private FusedLocationProviderClient locationClient;
    private final int REQUEST_PERMISSION_LOCATION=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

        locationClient = LocationServices.getFusedLocationProviderClient(this);
        btn = findViewById(R.id.getLocationBtn);
        textView = findViewById(R.id.currentLocationTxt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                        //get the Location here
                        locationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if(location != null){
                                    Double lat = location.getLatitude();
                                    Double longt = location.getLongitude();

                                    textView.setText(lat +" ,"+longt);
                                    Toast.makeText(CurrentLocation.this, "Success", Toast.LENGTH_LONG);
                                }
                            }
                        });
                    }else{
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION_LOCATION);
                    }
                }
            }
        });
    }
}