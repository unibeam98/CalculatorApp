package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.calculatorapp.Long.LengthChange;

import android.os.CancellationSignal;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.calculatorapp.Scale.scale;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private TextView equationText, resultText;
    private Button buttons[] = new Button[25];

    private CheckInput checkInput;

    private CounterByEquation counterByEquation;

    //布局中的按钮id
    private int buttonIds[] = new int[]{
            R.id.btn_AC, R.id.btn_Del, R.id.btn_Pre, R.id.btn_division, R.id.btn_N7, R.id.btn_N8,
            R.id.btn_N9, R.id.btn_mul, R.id.btn_N4, R.id.btn_N5, R.id.btn_N6, R.id.btn_sub,
            R.id.btn_N1, R.id.btn_N2, R.id.btn_N3, R.id.btn_add, R.id.btn_tra, R.id.btn_N0,
            R.id.btn_point, R.id.btn_equal,R.id.Involution,R.id.LBracket,R.id.RBracket,
            R.id.Reciprocal,R.id.Factorial
    };
    //等式
    private String equation = "";




    @Override
    public void onPerformDirectAction(String actionId, Bundle arguments, CancellationSignal cancellationSignal, Consumer<Bundle> resultListener) {
        super.onPerformDirectAction(actionId, arguments, cancellationSignal, resultListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        init();
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        Button button = (Button) findViewById(id);
        String text = button.getText().toString();
        switch (id) {
            //数字按钮
            case R.id.btn_N1:
            case R.id.btn_N2:
            case R.id.btn_N3:
            case R.id.btn_N4:
            case R.id.btn_N5:
            case R.id.btn_N6:
            case R.id.btn_N7:
            case R.id.btn_N8:
            case R.id.btn_N9:
            case R.id.btn_N0:
                checkInput.setEquation(equation);
                if (checkInput.checkNumberInput()) {
                    equation = checkInput.getEquation();
                    equation += text;
                }
                break;

            //点击+-*/
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_division:
                checkInput.setEquation(equation);
                if (checkInput.checkOperationsInput()) {
                    equation = checkInput.getEquation();
                    equation += text;
                }
                else{
                    Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                }
                break;

            //点击退格键
            case R.id.btn_Del:
                checkInput.setEquation(equation);
                checkInput.backSpace();
                equation = checkInput.getEquation();
                break;

            //清空键
            case R.id.btn_AC:
                equation = "";
                resultText.setText("");
                break;

            //小数点按钮
            case R.id.btn_point:
                checkInput.setEquation(equation);
                if (checkInput.checkPointInput()) {
                    equation = checkInput.getEquation();
                    equation += text;
                }
                break;

            //百分号按键
            case R.id.btn_Pre:
                checkInput.setEquation(equation);
                if (checkInput.checkRemainderInput())
                    equation += '%';
                break;

            //等于按钮
            case R.id.btn_equal:
                checkInput.setEquation(equation);
                if(checkInput.checkEqual())
                    resultText.setText(new CounterByEquation(equation).solveEquation());
                break;

                //x^y
            case R.id.Involution:
                checkInput.setEquation(equation);
                if(checkInput.checkInvolutionInput())
                    equation+="^";
                break;
            /*
             * 倒数
             * */
            case R.id.Reciprocal:
                checkInput.setEquation(equation);
                if(checkInput.checkReciprocalInput())
                    equation+="^(-1)";
                break;
            /*
             * 左括号
             * */
            case R.id.LBracket:
                checkInput.setEquation(equation);
                if(checkInput.checkLBracketInput())
                    equation+="(";
                break;
            /*
             * 右括号
             * */
            case R.id.RBracket:
                checkInput.setEquation(equation);
                if(checkInput.checkRBracketInput())
                    equation+=')';
                break;
            /*
             * 求阶乘
             * */
            case R.id.Factorial:
                checkInput.setEquation(equation);
                if(checkInput.checkFactorialInput())
                    equation+='!';
                break;

        }
        equationText.setText(equation);
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
                Intent intent = new Intent(MainActivity.this,help.class);
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


        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, LengthChange.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(MainActivity.this, scale.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void init () {
        int length = buttons.length;
        for (int i = 0; i < length; i++) {
            buttons[i] = (Button) findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        equationText = (TextView) findViewById(R.id.equation);
        resultText = (TextView) findViewById(R.id.result);
        checkInput = new CheckInput();
        counterByEquation = new CounterByEquation(equation);
    }

}
