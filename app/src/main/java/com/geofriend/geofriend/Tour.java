package com.geofriend.geofriend;

public class Tour {
    private int maximun=5;                             //maximun landmark can include
    private int Count=0;
    private String Name;
    private String Creator;
    private String Intro;
    private LandMark[] LandMark = new LandMark[maximun];
    private String[] LandMarkComment;
    private int Code;
    private int Rating;
    //cuurent landmark included

    public Tour() {
    }

    public Tour(int count, String name, String creator, String intro, com.geofriend.geofriend.LandMark[] landMark, String[] landMarkComment, int code, int rating) {
        Count = count;
        Name = name;
        Creator = creator;
        Intro = intro;
        LandMark = landMark;
        LandMarkComment = landMarkComment;
        Code = code;
        Rating = rating;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public com.geofriend.geofriend.LandMark[] getLandMark() {
        return LandMark;
    }

    public void setLandMark(com.geofriend.geofriend.LandMark[] landMark) {
        LandMark = landMark;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String[] getLandMarkComment() {
        return LandMarkComment;
    }

    public void setLandMarkComment(String[] landMarkComment) {
        LandMarkComment = landMarkComment;
    }

    public void setCount(int count) {
        this.Count = count;
    }

    //add LandMark
    public void addLandMark(LandMark l){
        LandMark[Count]=l;
        Count++;
    }

    //remove from database
    public void removeTour(){

    }

    //add this object to database(use as create new object)
    public void addTour(){

    }

    //update object to database(use as modify exist object)
    public void updateTour(int count, String name, String creator, String intro, com.geofriend.geofriend.LandMark[] landMark, String[] landMarkComment, int code, int rating){
        Tour old = new Tour(Count,Name,Creator,Intro,LandMark,LandMarkComment,Code,Rating);
        setCount(count);
        setName(name);
        setIntro(intro);
        setLandMark(landMark);
        setLandMarkComment(landMarkComment);
        setCode(code);
        setRating(rating);

        //replace the old object with the new object in database
        //
    }
}
