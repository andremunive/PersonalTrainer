package com.andres.personaltrainer.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.andres.personaltrainer.R;

public class payments extends AppCompatActivity {

    private Window window;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        initViews();
        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    private void initViews(){
        usuario = getIntent().getStringExtra("Usuario");
    }

    public void mensualidadClick(View view){
        Intent intent = new Intent(this, mensualidad.class);
        intent.putExtra("Usuario", usuario);
        startActivity(intent);
    }
}