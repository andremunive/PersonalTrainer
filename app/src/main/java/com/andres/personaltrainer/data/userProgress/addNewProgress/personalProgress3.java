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

public class personalProgress3 extends AppCompatActivity {

    //Change barr color
    private Window window;

    //Strings from the last view
    private String pantorrillaIzq, pantorrillaDer, cuadricepsIzq, cuadricepsDer, gluteos, date, week, peso;

    //Views Objects
    private EditText abdomen, espalda, brazoIzq, brazoDer, antebrazoIzq, antebrazoDer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_progress3);

        initViewsObjects();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    private void initViewsObjects(){
        //Strings from the last view
        pantorrillaIzq = getIntent().getStringExtra("pantorrillaIzq");
        pantorrillaDer = getIntent().getStringExtra("pantorrillaDer");
        cuadricepsIzq = getIntent().getStringExtra("cuadricepsIzq");
        cuadricepsDer = getIntent().getStringExtra("cuadricepsDer");
        gluteos = getIntent().getStringExtra("gluteos");
        date = getIntent().getStringExtra("date");
        week = getIntent().getStringExtra("week");
        peso = getIntent().getStringExtra("peso");

        //views Objects
        abdomen = findViewById(R.id.abdomenTxt);
        espalda = findViewById(R.id.espaldaTxt);
        brazoIzq = findViewById(R.id.brazoIzqTxt);
        brazoDer = findViewById(R.id.brazoDerTxt);
        antebrazoIzq = findViewById(R.id.antebrazoIzqTxt);
        antebrazoDer = findViewById(R.id.antebrazoDerTxt);
    }

    private boolean check(){
        if(!abdomen.getText().toString().isEmpty()){
            if(!espalda.getText().toString().isEmpty()){
                if(!brazoIzq.getText().toString().isEmpty()){
                    if(!brazoDer.getText().toString().isEmpty()){
                        if(!antebrazoIzq.getText().toString().isEmpty()){
                            if(!antebrazoDer.getText().toString().isEmpty()){
                                return true;
                            }else{
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    }
                    else{
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
            showPP4(abdomen.getText().toString(), espalda.getText().toString(),
                    brazoIzq.getText().toString(), brazoDer.getText().toString(),
                    antebrazoIzq.getText().toString(), antebrazoDer.getText().toString());
        }else{
            Toast.makeText(this,
                    "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPP4(String abdomen, String espalda, String bIzq, String bDer, String aIzq, String aDer){
        Intent intent = new Intent(this, personalProgress4.class);
        intent.putExtra("pantorrillaIzq", pantorrillaIzq);
        intent.putExtra("pantorrillaDer", pantorrillaDer);
        intent.putExtra("cuadricepsIzq", cuadricepsIzq);
        intent.putExtra("cuadricepsDer", cuadricepsDer);
        intent.putExtra("gluteos", gluteos);
        intent.putExtra("date", date);
        intent.putExtra("week", week);
        intent.putExtra("peso", peso);
        intent.putExtra("abdomen", abdomen);
        intent.putExtra("espalda", espalda);
        intent.putExtra("brazoIzq", bIzq);
        intent.putExtra("brazoDer", bDer);
        intent.putExtra("antebrazoIzq", aIzq);
        intent.putExtra("antebrazoDer", aDer);
        startActivity(intent);
    }

}