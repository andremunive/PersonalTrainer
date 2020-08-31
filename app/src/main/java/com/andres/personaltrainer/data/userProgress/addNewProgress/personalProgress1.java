package com.andres.personaltrainer.data.userProgress.addNewProgress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.andres.personaltrainer.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class personalProgress1 extends AppCompatActivity {

    //Views Objects
    private EditText date, week, peso;

    //Change barr color
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_progress1);

        initViewsObjects();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    private void initViewsObjects(){
        date = findViewById(R.id.dateTxt);
        date.setText(actualDate());

        week = findViewById(R.id.weekTxt);
        peso = findViewById(R.id.pesoTxt);
    }

    private String actualDate(){
        Date fecha = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");

        return format.format(fecha);
    }

    public void nextClick(View view){
        if(check()){
            showPP2(week.getText().toString(), date.getText().toString(), peso.getText().toString());
        }else{
            Toast.makeText(this,
                    "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }

    //Validar campos
    private boolean check(){
        if(!week.getText().toString().isEmpty()){
            if(!date.getText().toString().isEmpty()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    private void showPP2(String week, String date, String peso){
        Intent intent = new Intent(this, personalProgress2.class);
        intent.putExtra("Week", week);
        intent.putExtra("Date", date);
        intent.putExtra("Peso", peso);
        startActivity(intent);
    }

}