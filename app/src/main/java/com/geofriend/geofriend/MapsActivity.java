package com.geofriend.geofriend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LandmarkAdapter la = new LandmarkAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofriend_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Loads landmarks into the adapter instance
        la.loadLandmarks();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;



        if(!la.landmarks.isEmpty()) {
            for(int i = 0; i < la.landmarks.size(); i++) {
                mMap.addMarker(new MarkerOptions().position(la.landmarks.get(i).getLocation()).title(la.landmarks.get(i).getName()));
            }
        }

        //Moves camera to a park nearby my house.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.66107816, -120.2600196), 16.0f));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int markerClick = Log.v("click", "Markerclick");
                Intent intent = new Intent(MapsActivity.this, LandmarkPopUpActivity.class);
                startActivity(intent);
                return false;
            }
        });

    }
}