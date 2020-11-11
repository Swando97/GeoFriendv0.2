package com.geofriend.geofriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class LandMarkList extends AppCompatActivity {

    LandMark l1;
    bridge b;
    ListView listView;
    ArrayList<LandMark>  arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_mark_list);

        listView=findViewById(R.id.listView);
        arrayList=new ArrayList<>();

        arrayList.add(0, new LandMark("Juniper Park", 50.66107816, -120.2600196, "A park in Juniper Ridge."));
        arrayList.add(1, new LandMark( "Juniper Dog Park", 50.66165625, -120.26141435, "A dog park in Juniper Ridge."));
        arrayList.add(2, new LandMark( "Juniper Roundabout", 50.661075, -120.262175, "A roundabout in Juniper Ridge."));


        /*b.addToArrayList();*/

        landmarkAdapterReal landmarkAdapterReal=new landmarkAdapterReal(this,R.layout.list_row,arrayList);
        listView.setAdapter(landmarkAdapterReal);

        //
        //
    }

    /*public void arrayListitem(){
        b.addToArrayList();
        arrayList=b.arrayList;

    }*/
}