package com.example.calculatorapp.Long;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.calculatorapp.R;

import java.util.List;

public class UnitAdapter extends ArrayAdapter<units> {

    private int resourceID;

    public UnitAdapter(Context context, int textViewResourceID, List<units> objects){
        super(context,textViewResourceID,objects);
        resourceID = textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        units units = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView unitName = (TextView)view.findViewById(R.id.unit_name);
        unitName.setText(units.getName());
        return view;
    }
}
