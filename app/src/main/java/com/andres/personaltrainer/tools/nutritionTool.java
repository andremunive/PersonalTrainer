package com.andres.personaltrainer.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.andres.personaltrainer.R;

import java.io.IOException;

public class nutritionTool extends AppCompatActivity {

    private Window window;

    private EditText peso;

    private RadioButton bajar, mantener, subir;

    public double pesoLb;
    public int goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tool);

        initViewsObjects();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    private void initViewsObjects(){
        //RadioButtons
        subir = findViewById(R.id.subirBtn);
        mantener = findViewById(R.id.mantenerBtn);
        bajar = findViewById(R.id.bajarBtn);

        //EditText
        peso = findViewById(R.id.pesoTxt);
    }

    public void nextClick(View view){
        if(!peso.getText().toString().isEmpty()){
            try {

                if(bajar.isChecked()){
                    showInfoView();
                }else if(mantener.isChecked()){
                    showInfoView();
                }else if(subir.isChecked()){
                    showInfoView();
                }else{
                    Toast.makeText(this,
                            "Seleccionar un objetivo", Toast.LENGTH_SHORT).show();
                }


            }catch(Exception e){
                Toast.makeText(this, "Error en nutritionTool.Java(63)", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this,
                    "Completar información", Toast.LENGTH_SHORT).show();
        }

    }


    private void showInfoView(){
        pesoLb = Integer.parseInt(peso.getText().toString()) *2.2;
        if(bajar.isChecked()){
            goal = 0;
        }else if(mantener.isChecked()){
            goal = 1;

        }else if(subir.isChecked()){
            goal = 2;

        }

        String pesoTemporal = String.valueOf(pesoLb);
        String metaTemporal = String.valueOf(goal);

        Intent intent = new Intent(this, nutritionInfo.class);
        intent.putExtra("Peso", pesoTemporal);
        intent.putExtra("Goal", metaTemporal);
        startActivity(intent);
    }

}