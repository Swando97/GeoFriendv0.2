package com.geofriend.geofriend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class CurrentLocation extends AppCompatActivity {

    private Button btn;
    private TextView textView, gpsText;
    private FusedLocationProviderClient locationClient;
    private final int REQUEST_PERMISSION_LOCATION = 2;
    private LocationAddressResultReceiver addressResultReceiver;
    private Location currentLocation;
    private LocationCallback locationCallback;
    private GeofencingClient geofencingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

        addressResultReceiver = new LocationAddressResultReceiver(new Handler());
        textView = findViewById(R.id.currentLocationTxt);
        gpsText = findViewById(R.id.coordinates);
        //geofencingClient = LocationServices.getGeofencingClient(this);

        locationClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                getAddress();
            }
        };
        startLocationUpdates();
    }

    @SuppressWarnings("MissingPermission")
    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new
                            String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION_LOCATION);
        } else {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(2000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getAddress() {
        if (!Geocoder.isPresent()) {
            Toast.makeText(CurrentLocation.this, "Can't find current address, ",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this,GetAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }

    /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
            int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                Toast.makeText(this, "Location permission not granted, " + "restart the app if you want the feature",
                        Toast.LENGTH_SHORT).show();
            }
        }
    } */

    private class LocationAddressResultReceiver extends ResultReceiver implements com.geofriend.geofriend.LocationAddressResultReceiver {
        LocationAddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == 0) {
                Log.d("Address", "Location null retrying");
                getAddress();
            }
            if (resultCode == 1) {
                Toast.makeText(CurrentLocation.this, "Address not found, ", Toast.LENGTH_SHORT).show();
            }
            String currentAdd = resultData.getString("address_result");
            showResults(currentAdd);
        }

        private void showResults(String currentAdd) {
            textView.setText(currentAdd);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();

        btn = findViewById(R.id.getLocationBtn);

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

                                    gpsText.setText(lat +" ,"+longt);
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

    @Override
    protected void onPause() {
        super.onPause();
        locationClient.removeLocationUpdates(locationCallback);
    }
}
