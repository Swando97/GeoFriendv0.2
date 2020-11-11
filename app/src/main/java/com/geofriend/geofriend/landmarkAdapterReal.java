package com.geofriend.geofriend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class landmarkAdapterReal extends ArrayAdapter<LandMark> {
    private Context mcontext;
    private int mResource;

    public landmarkAdapterReal(@NonNull Context context, int resource, @NonNull ArrayList<LandMark> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @ NonNull ViewGroup parent){
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        //ImageView imageView=convertView.
        TextView txtName=convertView.findViewById(R.id.txtName);
        TextView txtDesc=convertView.findViewById(R.id.txtDesc);

        txtName.setText(getItem(position).getName());
        txtDesc.setText(getItem(position).getDesc());
        return convertView;
    }
}
