package com.geofriend.geofriend;

import java.util.ArrayList;
import java.util.List;

public class LandmarkAdapter {

    public static List<LandMark> landmarks = new ArrayList<LandMark>();

    public LandmarkAdapter(){

    }

    public void loadLandmarks(){
        landmarks.add(0, new LandMark(1, "Juniper Park", 50.66107816, -120.2600196, "A park in Juniper Ridge."));
        landmarks.add(1, new LandMark(2, "Juniper Dog Park", 50.66165625, -120.26141435, "A park in Juniper Ridge."));
    }



}
