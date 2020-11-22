package com.geofriend.geofriend;

import android.util.Log;

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



    public void updateUserData(String userID){

        userID = getUserID();

        DocumentReference documentReference = db.collection("users").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("uID", userID);
        user.put("userLandmarks", lma.ulandmarks);
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

                        List<Map<String, Object>> userLandmarks = (List<Map<String, Object>>) document.get("userLandmarks");

                        //List<LandMark> ulandmarks = document.toObject(LandmarkDocument.class).landmarks;
                        Log.v("DatabaseRead", "Successfully read from the database: "+userLandmarks.toString());

                    }
                }
            }
        });

    }




    public String getUserID(){
        return mAuth.getCurrentUser().getUid();
    }






}

