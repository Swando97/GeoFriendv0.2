package com.geofriend.geofriend;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DatabaseConnection {

    LandmarkMapAdapter lma = new LandmarkMapAdapter();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static List<LandMark> userLandmarks;
    public static List<LandMark> mapLandmarks;
    public static List<LandMark> discoveredLandmarks = new ArrayList<>();



    public void updateUserData(String userID){

        userID = getUserID();

        DocumentReference documentReference = db.collection("users").document(userID);
        //DocumentReference documentReference = db.collection("users").document("master");
        Map<String, Object> user = new HashMap<>();


        user.put("landmarks", LandmarkMapAdapter.userLandmarks);


        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Log.d("onSuccess", "Successfully wrote to the database.");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //Toast.makeText(LoginActivity.this, "User Creation failed.", Toast.LENGTH_SHORT).show();
                Log.d("onSuccess", "Failed to write to the database.");

            }
        });
    }



    public void readUserData(String userID){

        userID = getUserID();

        CollectionReference usersRef = db.collection("users");
        DocumentReference usersIdRef = usersRef.document(userID);
        usersIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {

                        //CONTAINS LIST OF LandMark objects

                        DatabaseConnection.discoveredLandmarks = document.toObject(LandmarkDocument.class).landmarks;

                        //DISPLAYS CONTENT OF ARRAY
                        for(int i=0; i<discoveredLandmarks.size();i++){
                            Log.v("DatabaseRead", "Read: "+DatabaseConnection.discoveredLandmarks.get(i).getID());
                        }

                    }
                    if(!document.exists()){
                        Log.v("DatabaseRead","Read Failure.");
                    }

                }
            }
        });
    }

    public void readLandmarkData(){

        CollectionReference usersRef = db.collection("users");
        DocumentReference usersIdRef = usersRef.document("master");
        usersIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {

                        //CONTAINS LIST OF MAPS
                        DatabaseConnection.mapLandmarks = document.toObject(LandmarkDocument.class).landmarks;

                        //DISPLAYS CONTENT OF ARRAY
                        for(int i=0; i<mapLandmarks.size();i++){
                            Log.v("DatabaseRead", "Read: "+DatabaseConnection.mapLandmarks.get(i).getName());
                        }

                        Log.v("DatabaseRead", "Database read successful.");

                    }
                    if(!document.exists()){
                        Log.v("DatabaseRead","Read Failure.");
                    }

                }
            }
        });
    }

    public String getUserID(){
        return mAuth.getCurrentUser().getUid();
    }

}

