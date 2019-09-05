package com.example.calculatorapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class helpActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        imageView = (ImageView)findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.my_icon);
    }

}
