package com.geofriend.geofriend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LandmarkListAdapter extends ArrayAdapter<LandMark> {
    private Context mContext;
    private int mResource;

    public LandmarkListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<LandMark> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);


        ImageView imageView = convertView.findViewById(R.id.locationImage);
        TextView textLocationName = convertView.findViewById(R.id.txtLocationName);
        TextView textLocationDesc = convertView.findViewById(R.id.txtLocationDesc);

        imageView.setImageResource(getItem(position).getImage());
        textLocationName.setText(getItem(position).getName());
        textLocationDesc.setText(getItem(position).getDesc());

        return convertView;
    }
}
