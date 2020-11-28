package com.geofriend.geofriend;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LandmarkMapAdapter {

    public static ArrayList<LandMark> landmarks = new ArrayList<LandMark>();
    public static ArrayList<LandMark> userLandmarks = new ArrayList<LandMark>();

    public LandmarkMapAdapter(){

    }

    public static boolean containsID(Collection<LandMark> c, int id){
        for(LandMark l : c) {
            if(l != null && l.getID() == id){
                return true;
            }
        }
        return false;
    }

    //Pulls Landmarks from Database

    public static void loadLandmarks(){
        for(int i=0; i<DatabaseConnection.mapLandmarks.size();i++){
            landmarks.add(i, DatabaseConnection.mapLandmarks.get(i));
        }
        landmarks.add(54, new LandMark(55, "GeoFence Test",50.660465686907706,-120.26588671471292, "House",R.drawable.stu_union, true));
        landmarks.add(55, new LandMark(56, "GeoFence Test",37.4221,-122.0841, "House",R.drawable.stu_union, true));
    }

    public static void loadDiscoveredLandmarks(){
        for(int i=0; i<DatabaseConnection.discoveredLandmarks.size();i++){
            userLandmarks.add(i, DatabaseConnection.discoveredLandmarks.get(i));
        }
    }

}
