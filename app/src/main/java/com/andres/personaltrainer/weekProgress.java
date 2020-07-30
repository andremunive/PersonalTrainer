package com.andres.personaltrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

public class weekProgress extends AppCompatActivity {

    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_progress);

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }
}