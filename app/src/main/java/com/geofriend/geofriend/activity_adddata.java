package com.geofriend.geofriend;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class activity_adddata extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

    }



    public void submit(View v){
        EditText tname=(EditText)findViewById(R.id.input_name);
        EditText tlat=(EditText)findViewById(R.id.input_lat);
        EditText tlong=(EditText)findViewById(R.id.input_long);
        EditText tdes=(EditText)findViewById(R.id.input_desc);
        String rname=tname.getText().toString();
        double rlat=Double.parseDouble(tlat.getText().toString());
        double rlong=Double.parseDouble(tlong.getText().toString());
        String rdesc=tdes.getText().toString();

//        LandMark l1=new LandMark(rname,rlat,rlong,rdesc);

//        bridge b=new bridge();
//        b.addData(l1);


    }

}
