package com.example.calculatorapp.Scale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.calculatorapp.Long.LengthChange;

import com.example.calculatorapp.MainActivity;
import com.example.calculatorapp.R;
import com.example.calculatorapp.help;
import com.google.android.material.navigation.NavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class scale extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    private TextView value1, value2,unit1,unit2;

    private String resultUnit="",backdata_unit1,backdata_unit2;

    private CheckInput_scale checkInput_unit;

    private ScaleChange scaleChange;

    private Button buttons[] = new Button[13];

    private int buttonIDs[] = new int[]{
            R.id.btn_N0,R.id.btn_N1, R.id.btn_N2, R.id.btn_N3,R.id.btn_N4, R.id.btn_N5, R.id.btn_N6,
            R.id.btn_N7, R.id.btn_N8, R.id.btn_N9,R.id.btn_point,R.id.AC_change,R.id.Del_change
    };

    private int n,i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
        Toolbar toolbar1 = findViewById(R.id.toolbar_scale);
        setSupportActionBar(toolbar1);
        unit1 = (TextView) findViewById(R.id.unit1_scale);
        unit2 = (TextView) findViewById(R.id.unit2_scale);
        unit1.setText("十进制");
        unit2.setText("八进制");
        unit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(scale.this, scale_unit_activity.class);
                startActivityForResult(intent1,1);

            }
        });

        unit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(scale.this,scale_unit_activity.class);
                startActivityForResult(intent2,2);

            }
        });

        init();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_length);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
                Toast.makeText(scale.this, backdata_unit1, Toast.LENGTH_SHORT).show();
                unit1.setText(backdata_unit1);
                resultUnit = "";
               // value2.setText(new LongChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
            }
        }
        else {
            if (requestCode == 2) {
                backdata_unit2 = data.getStringExtra("data_return_unit");
                Toast.makeText(scale.this, backdata_unit2, Toast.LENGTH_SHORT).show();
                unit2.setText(backdata_unit2);
                resultUnit = "";
                //value2.setText(new LongChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
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
                Intent intent = new Intent(scale.this, help.class);
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
            Intent intent = new Intent(scale.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_gallery) {
            Intent intent1 = new Intent(scale.this, LengthChange.class);
            startActivity(intent1);
            finish();

        } else if (id == R.id.nav_share) {

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
        if(unit1.getText().toString().equals("八进制")){
            switch (id_unit){
                case R.id.btn_N0:
                case R.id.btn_N1:
                case R.id.btn_N2:
                case R.id.btn_N3:
                case R.id.btn_N4:
                case R.id.btn_N5:
                case R.id.btn_N6:
                case R.id.btn_N7:
//            case R.id.btn_N8:
//            case R.id.btn_N9:
                    n++;
                    checkInput_unit.setEquationUnit(resultUnit);
                    if(checkInput_unit.checkNumberUnit() && n <= 9){
                        resultUnit = checkInput_unit.getEquationUnit();
                        resultUnit += string_unit;
                    }
                    value2.setText(new ScaleChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
                    break;
                case R.id.Del_change:
                    n--;
                    checkInput_unit.setEquationUnit(resultUnit);
                    checkInput_unit.BackSpaceUnit();
                    resultUnit = checkInput_unit.getEquationUnit();
                    value2.setText(new ScaleChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
                    break;
                case R.id.AC_change:
                    n = 0;
                    resultUnit = "";
                    value1.setText(resultUnit);
                    value2.setText(resultUnit);
                    break;
//                case R.id.btn_point:
//                    checkInput_unit.setEquationUnit(resultUnit);
//                    if(checkInput_unit.checkPointUnit()){
//                        resultUnit = checkInput_unit.getEquationUnit();
//                        resultUnit += string_unit;
//                    }
//                    break;
            }
        }
        else if(unit1.getText().toString().equals("二进制")){
            switch (id_unit){
                case R.id.btn_N0:
                case R.id.btn_N1:
//                case R.id.btn_N2:
//                case R.id.btn_N3:
//                case R.id.btn_N4:
//                case R.id.btn_N5:
//                case R.id.btn_N6:
//                case R.id.btn_N7:
//            case R.id.btn_N8:
//            case R.id.btn_N9:
                    n++;
                    checkInput_unit.setEquationUnit(resultUnit);
                    if(checkInput_unit.checkNumberUnit() && n <= 9){
                        resultUnit = checkInput_unit.getEquationUnit();
                        resultUnit += string_unit;
                    }
                    value2.setText(new ScaleChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
                    break;
                case R.id.Del_change:
                    n--;
                    checkInput_unit.setEquationUnit(resultUnit);
                    checkInput_unit.BackSpaceUnit();
                    resultUnit = checkInput_unit.getEquationUnit();
                    value2.setText(new ScaleChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
                    break;
                case R.id.AC_change:
                    n = 0;
                    resultUnit = "";
                    value1.setText(resultUnit);
                    value2.setText(resultUnit);
                    break;
//                case R.id.btn_point:
//                    checkInput_unit.setEquationUnit(resultUnit);
//                    if(checkInput_unit.checkPointUnit()){
//                        resultUnit = checkInput_unit.getEquationUnit();
//                        resultUnit += string_unit;
//                    }
//                    break;
            }
        }
        else {
            switch (id_unit) {
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
                    if (checkInput_unit.checkNumberUnit() && n <= 9) {
                        resultUnit = checkInput_unit.getEquationUnit();
                        resultUnit += string_unit;
                    }
                    value2.setText(new ScaleChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
                    break;
                case R.id.Del_change:
                    n--;
                    checkInput_unit.setEquationUnit(resultUnit);
                    checkInput_unit.BackSpaceUnit();
                    resultUnit = checkInput_unit.getEquationUnit();
                    value2.setText(new ScaleChange(resultUnit,unit1.getText().toString(),unit2.getText().toString()).count());
                    break;
                case R.id.AC_change:
                    n = 0;
                    resultUnit = "";
                    value1.setText(resultUnit);
                    value2.setText(resultUnit);
                    break;
//                case R.id.btn_point:
//                    checkInput_unit.setEquationUnit(resultUnit);
//                    if (checkInput_unit.checkPointUnit()) {
//                        resultUnit = checkInput_unit.getEquationUnit();
//                        resultUnit += string_unit;
//                    }
//                    break;
            }
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
        value1 = (TextView)findViewById(R.id.value1_scale);
        value2 = (TextView)findViewById(R.id.value2_scale);
        checkInput_unit = new CheckInput_scale();
        scaleChange = new ScaleChange(resultUnit,unit1.getText().toString(),unit2.getText().toString());
    }

}
