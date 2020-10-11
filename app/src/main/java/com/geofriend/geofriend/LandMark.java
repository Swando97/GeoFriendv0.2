package com.geofriend.geofriend;

public class LandMark {
    private String Name;
    private double Location;
    private String Info;
    private int Code;

    public LandMark() {
    }

    public LandMark(String name, double location, String info, int code) {
        Name = name;
        Location = location;
        Info = info;
        Code = code;
    }

    //copy constructor using in modify landmark
    public LandMark(LandMark old) {
        Name = old.getName();
        Location = old.getLocation();
        Info = old.getInfo();
        Code = old.getCode();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getLocation() {
        return Location;
    }

    public void setLocation(double location) {
        Location = location;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }


    //Working on Database

    //search LandMark by code in Database then assign the data in the object
    public void searchByCode(int Code){

    }

    //search LandMark by code in Database then assign the data in the object
    public void searchByLocation(double location){

    }

    //search LandMark by Name in Database then assign the data in the object
    public void searchByName(String name){

    }

    //remove from database
    public void removeLandMark(){

    }

    //add this object to database(use as create new object)
    public void addLanMark(){

    }

    //update object to database(use as modify exist object)
    public void updateLandMark(String name, double location, String info, int code){
        LandMark old=new LandMark(Name,Location,Info,Code);
        setName(name);
        setInfo(info);
        setLocation(location);
        setCode(code);

        //replace the old object with the new object in database

    }

}
