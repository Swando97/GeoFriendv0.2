package com.geofriend.geofriend;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;

public class bridge {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public LandMark l1;
    public Tour t1;
    
    //control and pass id 
    public String p;
    public void setp(String s){
        p=s;
    }

    public void passLandMark(LandMark l1){
        this.l1=l1;
    }

    public void passTour(Tour t1){
        this.t1=t1;
    }


    public void addData(LandMark l1){
        passLandMark(l1);
        db.collection("landmark").document(l1.getName()).set(l1);
    }

    public void addData(Tour t1){
        passTour(t1);
        db.collection("tour").document(t1.getName()).set(t1);
    }
    
/*to use this code we can have example:
        b.searchLandMark("OldMain");
        TextView t1=findViewById(R.id.testing);
        t1.setText(b.l1.getName());
    */
    public void searchLandMark(String s1){

        DocumentReference docRef = db.collection("landmark").document(s1);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                LandMark l2 = documentSnapshot.toObject(LandMark.class);
                String name=l2.getName();
                l1.setName(name);
                String desc=l2.getDesc();
                l1.setDesc(desc);
               int id=l2.getID();
                l1.setID(id);
               /* LatLng l=l2.getLocation();
                l1.setLocation(l.latitude,l.longitude);*/
                //missing the LatLng because of error
            }
        });
    }
    
    
    //searching the document name by id
    /* to use this code, example
    b.searchLandMarkByID(4); //it assign the document id to string p
    b.searchLandMark(b.p);
    */
    public void searchLandMarkByID(int i){
        db.collection("landmark").whereEqualTo("id",i).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String s=document.getId();
                        setp(s);
                    }
                } else {
                     //Log.d(TAG, "Error getting documents: ", task.getException());
                    }
            }
        });
    }
    
    //search the document name by name
    //need to separate it with the searchLandMark into two step as sometime it need double click to work
    //make sure it search the document name first before running step2
    /*sample:
    String g;
    LandMark lc;
    public void step1(){
        b.searchLandMarkByName("Library");
        g=b.p;
        TextView t1=findViewById(R.id.testing);

        t1.setText(g);
    }
    
    public void step2(){
        b.searchLandMark(g);
        TextView t2=findViewById(R.id.testing2);

        t2.setText(b.l1.getDesc());
    }
    */
    public void searchLandMarkByName(String s){
        db.collection("landmark").whereEqualTo("name",s).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String s=document.getId();
                        setp(s);
                    }
                } else {
                    //Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }



    /*public Tour searchTour(String s1){
        DocumentReference docRef = db.collection("tour").document(s1);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Tour t2 = documentSnapshot.toObject(Tour.class);
                t1=t2;
            }
        });
        return t1;
    }*/

    public void deleteData(){

    }

    /*public void updateData(){

    }*/
}
