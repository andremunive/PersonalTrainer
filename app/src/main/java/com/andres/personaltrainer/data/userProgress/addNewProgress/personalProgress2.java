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

public class personalProgress2 extends AppCompatActivity {

    //Strings from last activity
    private String date, week, peso, user;

    //Views Objects
    private EditText pantorrillaIzq, pantorrillaDer, cuadricepsIzq, cuadricepsDer, gluteos;

    //Change barr color
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_progress2);

        initViewsObjects();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    private void initViewsObjects(){
        //Strings from las activity
        date = getIntent().getStringExtra("Date");
        week = getIntent().getStringExtra("Week");
        peso = getIntent().getStringExtra("Peso");
        user = getIntent().getStringExtra("user");

        //Views Objects
        pantorrillaIzq = findViewById(R.id.pantorrillaIzqTxt);
        pantorrillaDer = findViewById(R.id.pantorrillaDerTxt);
        cuadricepsIzq = findViewById(R.id.cuadricepsIzqTxt);
        cuadricepsDer = findViewById(R.id.cuadricepsDerTxt);
        gluteos = findViewById(R.id.gluteosTxt);
    }

    private boolean check(){
        if(!pantorrillaIzq.getText().toString().isEmpty()){
            if(!pantorrillaDer.getText().toString().isEmpty()){
                if(!cuadricepsIzq.getText().toString().isEmpty()){
                    if(!cuadricepsDer.getText().toString().isEmpty()){
                        if(!gluteos.getText().toString().isEmpty()){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void nextClick(View view){
        if(check()){
            showPP3(pantorrillaIzq.getText().toString(), pantorrillaDer.getText().toString(),
                    cuadricepsIzq.getText().toString(), cuadricepsDer.getText().toString(),
                    gluteos.getText().toString());
        }else{
            Toast.makeText(this,
                    "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }


    private void showPP3(String pIzq, String pDer, String cIzq, String cDer, String glu){
        Intent intent = new Intent(this, personalProgress3.class);
        intent.putExtra("pantorrillaIzq", pIzq);
        intent.putExtra("pantorrillaDer", pDer);
        intent.putExtra("cuadricepsIzq", cIzq);
        intent.putExtra("cuadricepsDer", cDer);
        intent.putExtra("gluteos", glu);
        intent.putExtra("date", date);
        intent.putExtra("week", week);
        intent.putExtra("peso", peso);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}