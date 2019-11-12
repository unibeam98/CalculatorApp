package com.example.calculatorapp.Long;

import android.os.Bundle;
import android.content.Intent;

import com.example.calculatorapp.Scale.scale;
import com.example.calculatorapp.MainActivity;
import com.example.calculatorapp.R;
import com.example.calculatorapp.help;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LengthChange extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    private TextView value1, value2,unit1,unit2;

    private String resultUnit="",backdata_unit1,backdata_unit2;

    private CheckInput_unit checkInput_unit;

    private LongChange longChange;

    private Button buttons[] = new Button[13];

    private int buttonIDs[] = new int[]{
            R.id.btn_N0,R.id.btn_N1, R.id.btn_N2, R.id.btn_N3,R.id.btn_N4, R.id.btn_N5, R.id.btn_N6,
            R.id.btn_N7, R.id.btn_N8, R.id.btn_N9,R.id.btn_point,R.id.AC_change,R.id.Del_change
    };

    private int n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_change);
        Toolbar toolbar = findViewById(R.id.toolbar_length);
        setSupportActionBar(toolbar);
        unit1 = (TextView) findViewById(R.id.unit1);
        unit2 = (TextView) findViewById(R.id.unit2);
        unit1.setText("千米");
        unit2.setText("米");
        unit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LengthChange.this,unit_activity.class);
                startActivityForResult(intent1,1);

            }
        });

        unit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LengthChange.this,unit_activity.class);
                startActivityForResult(intent2,2);

            }
        });

        init();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_length);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    //接受unit返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                backdata_unit1 = data.getStringExtra("data_return_unit");
                Toast.makeText(LengthChange.this, backdata_unit1, Toast.LENGTH_SHORT).show();
                unit1.setText(backdata_unit1);
                value2.setText(new LongChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
            }
        }
        else {
            if (requestCode == 2) {
                backdata_unit2 = data.getStringExtra("data_return_unit");
                Toast.makeText(LengthChange.this, backdata_unit2, Toast.LENGTH_SHORT).show();
                unit2.setText(backdata_unit2);
                value2.setText(new LongChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_exit:
                finish();
                break;
            case R.id.action_help:
                Intent intent = new Intent(LengthChange.this, help.class);
                startActivity(intent);
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(LengthChange.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(LengthChange.this, scale.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id_unit = view.getId();
        Button button = (Button)findViewById(id_unit);
        String string_unit = button.getText().toString();
        switch (id_unit){
            case R.id.btn_N0:
            case R.id.btn_N1:
            case R.id.btn_N2:
            case R.id.btn_N3:
            case R.id.btn_N4:
            case R.id.btn_N5:
            case R.id.btn_N6:
            case R.id.btn_N7:
            case R.id.btn_N8:
            case R.id.btn_N9:
                n++;
                checkInput_unit.setEquationUnit(resultUnit);
                if(checkInput_unit.checkNumberUnit() && n <= 9){
                    resultUnit = checkInput_unit.getEquationUnit();
                    resultUnit += string_unit;
                }
                value2.setText(new LongChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
                break;
            case R.id.Del_change:
                checkInput_unit.setEquationUnit(resultUnit);
                checkInput_unit.BackSpaceUnit();
                resultUnit = checkInput_unit.getEquationUnit();
                n--;
                break;
            case R.id.AC_change:
                resultUnit = "";
                value1.setText(resultUnit);
                value2.setText(resultUnit);
                n = 0;
                break;
            case R.id.btn_point:
//                n++;
                checkInput_unit.setEquationUnit(resultUnit);
                if(checkInput_unit.checkPointUnit()){
                    resultUnit = checkInput_unit.getEquationUnit();
                    resultUnit += string_unit;
                }
                break;
        }
        //结果
        value1.setText(resultUnit);
    }

    public void init () {
        int length = buttons.length;
        for (int i = 0; i < length; i++) {
            buttons[i] = (Button) findViewById(buttonIDs[i]);
            buttons[i].setOnClickListener(this);
        }
        value1 = (TextView)findViewById(R.id.value1);
        value2 = (TextView)findViewById(R.id.value2);
        checkInput_unit = new CheckInput_unit();
        longChange = new LongChange(resultUnit,unit1.getText().toString(),unit2.getText().toString());
    }

}

