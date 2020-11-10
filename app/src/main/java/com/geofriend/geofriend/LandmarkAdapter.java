package com.geofriend.geofriend;

import java.util.ArrayList;
import java.util.List;

public class
LandmarkAdapter {

    public static List<LandMark> landmarks = new ArrayList<LandMark>();

    public LandmarkAdapter(){

    }

    public void loadLandmarks(){
        landmarks.add(0, new LandMark("Juniper Park", 50.66107816, -120.2600196, "A park in Juniper Ridge."));
        landmarks.add(1, new LandMark( "Juniper Dog Park", 50.66165625, -120.26141435, "A dog park in Juniper Ridge."));
        landmarks.add(2, new LandMark( "Juniper Roundabout", 50.661075, -120.262175, "A roundabout in Juniper Ridge."));

    }



}
