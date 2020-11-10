package com.geofriend.geofriend;

import java.util.ArrayList;
import java.util.List;

public class LandmarkMapAdapter {

    public static ArrayList<LandMark> landmarks = new ArrayList<LandMark>();

    public LandmarkMapAdapter(){

    }

    public void loadLandmarks(){
//        landmarks.add(0, new LandMark(1, "Juniper Park", 50.66107816, -120.2600196, "A park in Juniper Ridge.",));
//        landmarks.add(1, new LandMark(2, "Juniper Dog Park", 50.66165625, -120.26141435, "A dog park in Juniper Ridge.",));
//        landmarks.add(2, new LandMark(3, "Juniper Roundabout", 50.661075, -120.262175, "A roundabout in Juniper Ridge.",));
//        landmarks.add(3, new LandMark(4, "TRU HOL", 50.675940, -120.360050, "Testing the description",));
        landmarks.add(4, new LandMark(5, "Kamloops Downtown", 52.675940, -124.360050, "Testing... description", R.drawable.image2));
        landmarks.add(5, new LandMark(6, "TRU HOL", 48.675940, -118.360050, "Testing ...", R.drawable.image1));
    }



}
