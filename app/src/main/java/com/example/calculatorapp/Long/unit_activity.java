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
        units dm = new units("分米");
        unitsList.add(dm);
        units cm = new units("厘米");
        unitsList.add(cm);
        units mm = new units("毫米");
        unitsList.add(mm);
        units nmi = new units("海里");
        unitsList.add(nmi);
        units mi = new units("英里");
        unitsList.add(mi);
        units fur = new units("弗隆");
        unitsList.add(fur);
        units ftm = new units("英寻");
        unitsList.add(ftm);
        units yd = new units("码");
        unitsList.add(yd);
        units ft = new units("英尺");
        unitsList.add(ft);
        units in = new units("英寸");
        unitsList.add(in);
        units gongli = new units("公里");
        unitsList.add(gongli);
        units li = new units("里");
        unitsList.add(li);
        units zhang = new units("丈");
        unitsList.add(zhang);
        units chi = new units("尺");
        unitsList.add(chi);
        units cun = new units("寸");
        unitsList.add(cun);
    }
}
