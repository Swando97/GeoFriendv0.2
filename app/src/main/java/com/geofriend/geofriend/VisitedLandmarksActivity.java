package com.geofriend.geofriend;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;


public class VisitedLandmarksActivity extends AppCompatActivity {

    private Button userLocationBttn;
    private ListView listViewLocationDisplay;
    private LandmarkMapAdapter lma = new LandmarkMapAdapter();
    private LandmarkListAdapter lla;
    DatabaseConnection databaseConnection = new DatabaseConnection();

    private GeofencingClient geofencingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

        geofencingClient = LocationServices.getGeofencingClient(this);

        databaseConnection.readUserData(databaseConnection.getUserID());

        //ListView instantiation
        listViewLocationDisplay = findViewById(R.id.listViewLocations);

        // Load Visited Landmarks into a local list.
        if (LandmarkMapAdapter.userLandmarks.isEmpty()){
            LandmarkMapAdapter.loadDiscoveredLandmarks();
        }


        //
        //ArrayList<LandMark> loadedLandmarks = (ArrayList<LandMark>) DatabaseConnection.userLandmarks;


        lla = new LandmarkListAdapter(this, R.layout.list_row, LandmarkMapAdapter.userLandmarks);




        // Populate ListView
        listViewLocationDisplay.setAdapter(lla);

        // Set onItemClickListener
        listViewLocationDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(VisitedLandmarksActivity.this, LandmarkPopUpActivity.class);
//                intent.putExtra("landmarkID", ""+position);

                Toast.makeText(VisitedLandmarksActivity.this, ""+LandmarkMapAdapter.userLandmarks.get(position).getName(), Toast.LENGTH_LONG).show();

                //Load the PopUpActivity Window
                //startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        // Initializing OnClick Listener for button to display User Current Location
        //userLocationBttn = findViewById(R.id.UserCurrentLocationDetailsBttn);


    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
