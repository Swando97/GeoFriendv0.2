package com.geofriend.geofriend;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LandMark {

    //GLOBALS
    private int mID;

    private String mName;

    private LatLng mLocation;
    private double mLatitude;
    private double mLongitude;

    private String mDesc;

    private int mImage;
    //GLOBALS

    public LandMark(int mID, String mName, double mLatitude, double mLongitude, String mDesc, int mImage) {

        // ID and NAME
        this.mID = mID;
        this.mName = mName;

        // LOCATION, LATITUDE, and LONGITUDE
        this.mLocation = new LatLng(mLatitude, mLongitude);
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;

        // LANDMARK DESCRIPTION
        this.mDesc = mDesc;

        // LANDMARK IMAGE
        this.mImage = mImage;

    }

    //copy constructor using in modify landmark
//    public LandMark(LandMark old) {
//        mName = old.getName();
//        mLocation = old.getLocation();
//        mDesc = old.getDesc();
//        mID = old.getID();
//    }



    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public LatLng getLocation() {
        return mLocation;
    }

    public void setLocation(double mLatitude, double mLongitude) {
        this.mLocation = new LatLng(mLatitude, mLongitude);
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public int getImage(){
        return mImage;
    }

    public void setImage(int mImage){
        this.mImage = mImage;
    }


    //Working on Database

    //search LandMark by code in Database then assign the data in the object
    public void searchByID(int ID){

    }

    //search LandMark by code in Database then assign the data in the object
    public void searchByLocation(LatLng searchLocation){

    }

    //search LandMark by Name in Database then assign the data in the object
    public void searchByName(String name){

    }

    //remove from database
    public void removeLandMark(){

    }

    //add this object to database(use as create new object)
    public void addLandMark(){

    }

/*
    //update object to database(use as modify exist object)
    public void updateLandMark(int mID, String mName, double mLatitude, double mLongitude, String mDesc){
        LandMark old = new LandMark(mID, mName, mLatitude, mLatitude, mDesc);
        setName(mName);
        setDesc(mDesc);
        setLocation(mLocation.latitude, mLocation.longitude);
        setID(mID);

        //replace the old object with the new object in database

    }
*/

}
