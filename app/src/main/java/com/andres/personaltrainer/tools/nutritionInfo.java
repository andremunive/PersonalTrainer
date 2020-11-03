package com.andres.personaltrainer.tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.GoalRow;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.andres.personaltrainer.R;

import java.text.DecimalFormat;
import java.util.Objects;

public class nutritionInfo extends AppCompatActivity {

    private EditText calorias, proteina, grasa, carbohidratos, fruta, agua;
    private double peso;
    private int goal;

    private DecimalFormat decimalFormat = new DecimalFormat("#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_info);

        initView();
    }

    private void initView(){
        //EditText
        calorias = findViewById(R.id.caloriasTxt);
        proteina = findViewById(R.id.proteinaTxt);
        grasa = findViewById(R.id.grasaTxt);
        carbohidratos = findViewById(R.id.carbohidratosTxt);
        fruta = findViewById(R.id.frutaTxt);
        agua = findViewById(R.id.aguaTxt);

        //Intents
        peso = Double.parseDouble(Objects.requireNonNull(getIntent().getStringExtra("Peso")));
        goal = Integer.parseInt(Objects.requireNonNull(getIntent().getStringExtra("Goal")));

        //Methods
        setResults();
    }

    private void setResults(){
        calculateCalories();
        calculateProtein();
        calculateGrease();
        calculateCarbs();
    }

    private void calculateCalories(){
        if(goal==0){
            double result = peso * 14;
            calorias.setText(decimalFormat.format(result));
            fruta.setText("200");
        }else if(goal==1){
            double result = peso * 16;
            calorias.setText(decimalFormat.format(result));
            fruta.setText("250");
        }else if(goal==2){
            double result = peso * 20;
            calorias.setText(decimalFormat.format(result));
            fruta.setText("300");
        }
    }

    private void calculateProtein(){
        double tempPeso = peso / 2.2;
        //Calculo la cantidad de agua a tomar

        agua.setText(decimalFormat.format(tempPeso*35));

        if(goal==0){
            double result = tempPeso * 1.5;
            proteina.setText(decimalFormat.format(result));
        }else if(goal==1){
            double result = tempPeso * 1.8;
            proteina.setText(decimalFormat.format(result));
        }else if(goal==2){
            double result = tempPeso * 2.1;
            proteina.setText(decimalFormat.format(result));
        }
    }

    private void calculateGrease(){
        double tempPeso = peso / 2.2;
        if(goal==0){
            double result = tempPeso * 1;
            grasa.setText(decimalFormat.format(result));
        }else if(goal==1){
            double result = tempPeso * 1.1;
            grasa.setText(decimalFormat.format(result));
        }else if(goal==2){
            double result = tempPeso * 1.3;
            grasa.setText(decimalFormat.format(result));
        }
    }

    private void calculateCarbs(){

        double prote = Double.parseDouble(proteina.getText().toString());
        double gras = Double.parseDouble(grasa.getText().toString());
        double cal = Double.parseDouble(calorias.getText().toString());

        double carbs = (cal - (prote*4)-(gras*9))/4;

        carbohidratos.setText(decimalFormat.format(carbs));

    }
}