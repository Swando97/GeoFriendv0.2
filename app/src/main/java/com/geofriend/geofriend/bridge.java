package com.geofriend.geofriend;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import android.view.View;

public class bridge {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public LandMark l1;
    public Tour t1;

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

    public LandMark searchLandMark(String s1){

        DocumentReference docRef = db.collection("landmark").document(s1);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                LandMark l2 = documentSnapshot.toObject(LandMark.class);
                l1=l2;
            }
        });
        return l1;
    }



    public Tour searchTour(String s1){
        DocumentReference docRef = db.collection("tour").document(s1);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Tour t2 = documentSnapshot.toObject(Tour.class);
                t1=t2;
            }
        });
        return t1;
    }

    public void deleteData(){

    }

    /*public void updateData(){

    }*/
}
