package com.example.calculatorapp.Long;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.calculatorapp.R;

import java.util.ArrayList;
import java.util.List;

public class unit_activity extends AppCompatActivity {

    private List<units> unitsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        initUnits();
        UnitAdapter adapter = new UnitAdapter(unit_activity.this,R.layout.unit_item,unitsList);
        ListView listView = (ListView)findViewById(R.id.unit_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                units units = unitsList.get(position);
                //Toast.makeText(unit_activity.this,units.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return_unit",units.getName());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initUnits() {
        units km = new units("千米");
        unitsList.add(km);
        units m = new units("米");
        unitsList.add(m);
    }
}
