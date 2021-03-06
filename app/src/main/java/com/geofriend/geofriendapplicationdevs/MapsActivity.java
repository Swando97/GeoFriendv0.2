package com.geofriend.geofriendapplicationdevs;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.Marker;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "MapActivity";
    private float GEOFENCE_RADIUS = 20;
    private String GEOFENCE_ID = "someID0";

    // Must always accept permissions!!! (Geofence Working)

    private final int BACKGROUND_LOCATION_ACCESS_REQUEST_CODE = 10002;
    private GeofencingClient geofencingClient;
    private GeofenceHelper geofenceHelper;

    private GoogleMap mMap;
    private UiSettings mUI;


    DatabaseConnection databaseConnection = new DatabaseConnection();

    private double cLat, cLng;

    // Location variables used to request permissions
    private final int REQUEST_PERMISSION_LOCATION = 2;
    private FusedLocationProviderClient fusedLocationClient;
    public LocationAddressResultReceiver addressResultReceiver;
    private Location currentLocation;
    private LocationCallback locationCallback;

    // Location variable used to display results
    //private LocationAddressResultReceiverTest addressResultReceiver;

    private TextView userLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofriend_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Loads landmarks into the adapter instance
        if (LandmarkMapAdapter.landmarks.isEmpty()) {
            LandmarkMapAdapter.loadLandmarks();
        }




//        userLocation = findViewById(R.id.landMarkTxt);

        addressResultReceiver = new LocationAddressResultReceiver(new Handler());

        // ----- This is used to display current location information -----
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                getAddress();

                //Moves camera to Current Position

                //mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));
            }
        };
        startLocationUpdates();

        // ----- Enables Map Options -----


        //create geofencing client
        geofencingClient = LocationServices.getGeofencingClient(this);
        geofenceHelper = new GeofenceHelper(this);


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
//         ----- Check the location permission status -----
//         Example: If not already granted, request for location permission


        mMap = googleMap;
        mMap.clear();
        startLocationUpdates();
        enableUserLocation();
        mUI = mMap.getUiSettings();

        mUI.setScrollGesturesEnabled(false);
        mUI.setCompassEnabled(false);
        mUI.setScrollGesturesEnabledDuringRotateOrZoom(false);
        mUI.setZoomControlsEnabled(false);
        mUI.setMyLocationButtonEnabled(false);

        mMap.setMinZoomPreference(1.5f);
        mMap.setMaxZoomPreference(18.7f);


        // Import map style
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }



        //THIS SECTION OF CODE IS RUN WHEN THE MAP OPENS UP
        //  IT CHECKS TO SEE IF THERE'S LANDMARKS IN THE LIST, THEN LOADS THEM INTO THE MAP
        if (!LandmarkMapAdapter.landmarks.isEmpty()) {
            for (int i = 0; i < LandmarkMapAdapter.landmarks.size(); i++) {
                //GETS LANDMARK AT POSITION i AND PUTS IT ONTO THE MAP
                mMap.addMarker(new MarkerOptions().position(new LatLng(LandmarkMapAdapter.landmarks.get(i).getLatitude(),LandmarkMapAdapter.landmarks.get(i).getLongitude())).title(LandmarkMapAdapter.landmarks.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.landmark)));
                //A GEOFENCE IS THEN BUILT AROUND THE LANDMARK
              

                if (Build.VERSION.SDK_INT >= 29) {
                    //We need background permission
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                          addCircle(new LatLng(LandmarkMapAdapter.landmarks.get(i).getLatitude(),LandmarkMapAdapter.landmarks.get(i).getLongitude()), GEOFENCE_RADIUS);

                        addGeofence(new LatLng(LandmarkMapAdapter.landmarks.get(i).getLatitude(),LandmarkMapAdapter.landmarks.get(i).getLongitude()), GEOFENCE_RADIUS, LandmarkMapAdapter.landmarks.get(i).getName());
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                            //We show a dialog and ask for permission
                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, BACKGROUND_LOCATION_ACCESS_REQUEST_CODE);
                        } else {
                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, BACKGROUND_LOCATION_ACCESS_REQUEST_CODE);
                        }
                    }

                } else {
                    addCircle(new LatLng(LandmarkMapAdapter.landmarks.get(i).getLatitude(),LandmarkMapAdapter.landmarks.get(i).getLongitude()), GEOFENCE_RADIUS);
                    addGeofence(new LatLng(LandmarkMapAdapter.landmarks.get(i).getLatitude(),LandmarkMapAdapter.landmarks.get(i).getLongitude()), GEOFENCE_RADIUS, LandmarkMapAdapter.landmarks.get(i).getName());
                }
            } // END OF LOOP
        } // END OF IF STATEMENT

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //get the Location here
                fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            cLat = location.getLatitude();
                            cLng = location.getLongitude();

                            //Toast.makeText(MapsActivity.this, cLat+", "+cLng, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
            }
        }

        //Moves camera to TRU.

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(50.6725100459571,-120.3652719974587))
                .zoom(18.5f)
                .bearing(315)
                .tilt(60)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));

        //SETS AN ON CLICK LISTENER FOR THE MARKERS

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                // WHEN MARKER IS CLICKED, THE MARKER ID IS PASSED THROUGH AN INTENT TO LandmarkPopUpActivity.class
                // THEN THE ACTIVITY IS OPENED
                //int markerClick = Log.v("click", "Markerclick");
                mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(LandmarkMapAdapter.landmarks.get(Integer.parseInt(marker.getId().substring(1))).getLatitude(), LandmarkMapAdapter.landmarks.get(Integer.parseInt(marker.getId().substring(1))).getLongitude())));

                Intent intent = new Intent(MapsActivity.this, LandmarkPopUpActivity.class);
                intent.putExtra("landmarkID", marker.getId().substring(1));
                startActivity(intent);

                return true;
            }
        });

    } // END OF onMapReady Method


    // Permission to access users current location
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
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }


    private void getAddress() {
        if (!Geocoder.isPresent()) {
            //Toast.makeText(MapsActivity.this, "Can't find current address, ", Toast.LENGTH_SHORT).show();
            Log.v("noAddress","Can't find current address");
            return;
        }
        Intent intent = new Intent(this, GetAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }


    private class LocationAddressResultReceiver extends ResultReceiver implements com.geofriend.geofriendapplicationdevs.LocationAddressResultReceiver {
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
                //Toast.makeText(MapsActivity.this, "Address not found, ", Toast.LENGTH_SHORT).show();
                Log.v("noAddress","Address not found.");
            }
            String currentAdd = resultData.getString("address_result");
            showResults(currentAdd);
        }

        private void showResults(String currentAdd) {
            Log.d("onReceive", "Current Address:\n"+cLat+", "+cLng);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
        //databaseConnection.readUserData(databaseConnection.getUserID());
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
        //databaseConnection.updateUserData(databaseConnection.getUserID());
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseConnection.updateUserData(databaseConnection.getUserID());
    }

    private void addCircle(LatLng latLng, float radius) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255, 14, 229, 237));
        circleOptions.fillColor(Color.argb(30, 14, 229, 237));
        circleOptions.strokeWidth(4);
        mMap.addCircle(circleOptions);
    }


    private void addGeofence(LatLng latLng, float radius, String id) {

        Geofence geofence = geofenceHelper.getGeofence(id, latLng, radius, Geofence.GEOFENCE_TRANSITION_ENTER);
        GeofencingRequest geofencingRequest = geofenceHelper.getGeofencingRequest(geofence);

        PendingIntent pendingIntent = geofenceHelper.getPendingIntent();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                //We need to show user a dialog for displaying why the permission is needed and then ask for the permission...
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
            }
            return;
        }
        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Geofence Added...");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String errorMessage = geofenceHelper.getErrorString(e);
                        Log.d(TAG, "onFailure: " + errorMessage);
                    }
                });

    }

    private void enableUserLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            //Ask for permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                //We need to show user a dialog for displaying why the permission is needed and then ask for the permission...
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
            }
        }
    }
}
