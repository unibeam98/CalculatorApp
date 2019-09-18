package com.example.calculatorapp.Scale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.calculatorapp.Long.UnitAdapter;
import com.example.calculatorapp.Long.units;
import com.example.calculatorapp.R;

import java.util.ArrayList;
import java.util.List;

public class scale_unit_activity extends AppCompatActivity {

    private List<units> unitsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scale_unit);
        initUnits();
        UnitAdapter adapter = new UnitAdapter(com.example.calculatorapp.Scale.scale_unit_activity.this,R.layout.unit_item,unitsList);
        ListView listView = (ListView)findViewById(R.id.scale_unit_list_view);
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
        units dec = new units("十进制");
        unitsList.add(dec);
        units oct = new units("八进制");
        unitsList.add(oct);
    }
}
