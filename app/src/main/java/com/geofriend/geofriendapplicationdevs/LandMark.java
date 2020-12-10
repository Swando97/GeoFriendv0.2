package com.geofriend.geofriendapplicationdevs;

import com.google.android.gms.maps.model.LatLng;

public class LandMark {

    //GLOBALS
    private int mID;

    private String mName;

    private LatLng mLocation;
    private double mLatitude;
    private double mLongitude;

    private String mDesc;

    private int mImage;

    private boolean isVisited;
    //GLOBALS

    public LandMark() {

    }

    public LandMark(int mID, String mName, double mLatitude, double mLongitude, String mDesc, int mImage, boolean isVisited) {

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

        // BOOLEAN VISITED FLAG
        this.isVisited = isVisited;

    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public LatLng getLocation() {
        return mLocation;
    }

    public double getLatitude(){
        return mLatitude;
    }

    public void setLatitude(double mLatitude){
        this.mLatitude = mLatitude;
    }

    public double getLongitude(){
        return mLongitude;
    }

    public void setLongitude(double mLongitude){
        this.mLongitude = mLongitude;
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

    public boolean isVisited(){
        return isVisited;
    }

    public void setVisited(boolean isVisited){
        this.isVisited = isVisited;
    }

}
