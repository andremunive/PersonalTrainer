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

public class personalProgress4 extends AppCompatActivity {

    //Change barr color
    private Window window;

    //Strings from the last view
    private String pantorrillaIzq, pantorrillaDer, cuadricepsIzq, cuadricepsDer, gluteos, date, week, peso,
            abdomen, espalda, brazoIzq, brazoDer, antebrazoIzq, antebrazoDer, user;

    //Views objects
    private EditText tricipital, bicipital, subescapular, suprailiaco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_progress4);

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
        abdomen = getIntent().getStringExtra("abdomen");
        espalda = getIntent().getStringExtra("espalda");
        brazoIzq = getIntent().getStringExtra("brazoIzq");
        brazoDer = getIntent().getStringExtra("brazoDer");
        antebrazoIzq = getIntent().getStringExtra("antebrazoIzq");
        antebrazoDer = getIntent().getStringExtra("antebrazoDer");
        user = getIntent().getStringExtra("user");

        //Views Object
        tricipital = findViewById(R.id.tricipitalTxt);
        bicipital = findViewById(R.id.bicipitalTxt);
        subescapular = findViewById(R.id.subescapularTxt);
        suprailiaco = findViewById(R.id.suprailiacoTxt);
    }

    public void nextClick(View view){
        if(check()){
            showPP5(tricipital.getText().toString(), bicipital.getText().toString(),
                    subescapular.getText().toString(), suprailiaco.getText().toString());
        }else{
            Toast.makeText(this,
                    "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean check(){
        if(!tricipital.getText().toString().isEmpty()){
            if(!bicipital.getText().toString().isEmpty()){
                if(!subescapular.getText().toString().isEmpty()){
                    if(!suprailiaco.getText().toString().isEmpty()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void showPP5(String tri, String bi, String sub, String sup){
        Intent intent = new Intent(this, progressSave.class);
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
        intent.putExtra("brazoIzq", brazoIzq);
        intent.putExtra("brazoDer", brazoDer);
        intent.putExtra("antebrazoIzq", antebrazoIzq);
        intent.putExtra("antebrazoDer", antebrazoDer);
        intent.putExtra("tricipital", tri);
        intent.putExtra("bicipital", bi);
        intent.putExtra("subescapular", sub);
        intent.putExtra("suprailiaco", sup);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}