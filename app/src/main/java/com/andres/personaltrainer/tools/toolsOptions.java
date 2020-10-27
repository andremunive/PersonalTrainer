package com.andres.personaltrainer.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.andres.personaltrainer.R;

public class toolsOptions extends AppCompatActivity {

    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_options);

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    public void nutritionClick(View view){
        Intent intent = new Intent(this, nutritionTool.class);
        startActivity(intent);
    }
}