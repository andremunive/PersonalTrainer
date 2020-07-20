package com.andres.personaltrainer.data.userProgress.addNewProgress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.andres.personaltrainer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class addProgress extends AppCompatActivity {

    private EditText pantorrillaIzq, pantorrillaDer, cuadricepsIzq, cuadricepsDer, gluteos, cadera, abdomen, espalda,
            brazoIzq, brazoDer, antebrazoIzq, antebrazoDer, peso, tricipital, bicipital, subescapular, suprailiaco,
            fat, week, fatMass, leanMass, metabolism;

    private TextView weekTitle;

    //Doubles
    //Suma de los pliegues
    private double suma = 0;

    //Constantes para la formula de Durnin
    private double HC1 = 1.1620, HC2 = 1.1631, HC3 = 1.1422, HC4 = 1.1620, HC5 = 1.1715,
            HM1 = 0.0630, HM2 = 0.0632, HM3 = 0.0544, HM4 = 0.0700, HM5 = 0.0779;

    private double MC1 = 1.1549, MC2 = 1.1599, MC3 = 1.1423, MC4 = 1.1333, MC5 = 1.1339,
            MM1 = 0.0678, MM2 = 0.0717, MM3 = 0.0632, MM4 = 0.0612, MM5 = 0.0645;

    //Porcentaje de grasa final
    private double porcentajeGrasa;

    //Strings
    private String user;

    //Firebase
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("clientes");
    private DatabaseReference dbRef2 = FirebaseDatabase.getInstance().getReference("clientProgress");

    private int edad;

    private DecimalFormat decimalFormat = new DecimalFormat("#.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_progress);
        initViews();

    }

    private void initViews() {
        //EditTexts
        pantorrillaIzq = findViewById(R.id.pantorrillaIzqTxt);
        pantorrillaDer = findViewById(R.id.pantorrillaDerTxt);
        cuadricepsIzq = findViewById(R.id.cuadricepsIzqTxt);
        cuadricepsDer = findViewById(R.id.cuadricepsDerTxt);
        gluteos = findViewById(R.id.gluteosTxt);
        cadera = findViewById(R.id.caderaTxt);
        abdomen = findViewById(R.id.abdomenTxt);
        espalda = findViewById(R.id.espaldaTxt);
        brazoIzq = findViewById(R.id.brazoIzqTxt);
        brazoDer = findViewById(R.id.brazoDerTxt);
        antebrazoDer = findViewById(R.id.antebrazoDerTxt);
        antebrazoIzq = findViewById(R.id.antebrazoIzqTxt);
        peso = findViewById(R.id.pesoTxt);
        tricipital = findViewById(R.id.tricipitalTxt);
        bicipital = findViewById(R.id.bicipitalTxt);
        subescapular = findViewById(R.id.subescapularTxt);
        suprailiaco = findViewById(R.id.suprailiacoTxt);
        fat = findViewById(R.id.fatPercentTxt);
        week = findViewById(R.id.weekTxt);
        fatMass = findViewById(R.id.fatMassTxt);
        leanMass = findViewById(R.id.leanMassTxt);
        metabolism = findViewById(R.id.metabolismTxt);


        //TextViews
        weekTitle = findViewById(R.id.weekTitle);

        //Stirngs
        user = getIntent().getStringExtra("usuario");


    }

    private void calculos() {

        double triceps, biceps, subescapula, suprailiac;

        triceps = Double.parseDouble(tricipital.getText().toString().trim());
        biceps = Double.parseDouble(bicipital.getText().toString().trim());
        subescapula = Double.parseDouble(subescapular.getText().toString().trim());
        suprailiac = Double.parseDouble(suprailiaco.getText().toString().trim());

        //Suma de todos los pliegues
        suma = triceps + biceps + subescapula + suprailiac;
    }

    public void calculateClick(View view) {

        if(check()){

        calculos();
        getGenero();


        }
    }

    //Método para validar todos los campos de texto
    private Boolean check() {
        if(!week.getText().toString().isEmpty()) {
            if (!pantorrillaIzq.getText().toString().isEmpty()) {
                if (!pantorrillaDer.getText().toString().isEmpty()) {
                    if (!cuadricepsIzq.getText().toString().isEmpty()) {
                        if (!cuadricepsDer.getText().toString().isEmpty()) {
                            if (!gluteos.getText().toString().isEmpty()) {
                                if (!cadera.getText().toString().isEmpty()) {
                                    if (!abdomen.getText().toString().isEmpty()) {
                                        if (!espalda.getText().toString().isEmpty()) {
                                            if (!brazoIzq.getText().toString().isEmpty()) {
                                                if (!brazoDer.getText().toString().isEmpty()) {
                                                    if (!antebrazoIzq.getText().toString().isEmpty()) {
                                                        if (!antebrazoDer.getText().toString().isEmpty()) {
                                                            if (!peso.getText().toString().isEmpty()) {
                                                                if (!tricipital.getText().toString().isEmpty()) {
                                                                    if (!bicipital.getText().toString().isEmpty()) {
                                                                        if (!subescapular.getText().toString().isEmpty()) {
                                                                            if (!suprailiaco.getText().toString().isEmpty()) {
                                                                                return true;
                                                                            } else {
                                                                                Toast.makeText(this,
                                                                                        "Rellenar todos los campos",
                                                                                        Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        } else {
                                                                            Toast.makeText(this,
                                                                                    "Rellenar todos los campos",
                                                                                    Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    } else {
                                                                        Toast.makeText(this,
                                                                                "Rellenar todos los campos",
                                                                                Toast.LENGTH_SHORT).show();
                                                                    }
                                                                } else {
                                                                    Toast.makeText(this,
                                                                            "Rellenar todos los campos",
                                                                            Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(this,
                                                                        "Rellenar todos los campos",
                                                                        Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(this,
                                                                    "Rellenar todos los campos",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(this,
                                                                "Rellenar todos los campos",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(this,
                                                            "Rellenar todos los campos",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(this,
                                                        "Rellenar todos los campos",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(this,
                                                    "Rellenar todos los campos",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(this,
                                            "Rellenar todos los campos",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this,
                                        "Rellenar todos los campos",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this,
                                    "Rellenar todos los campos",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this,
                                "Rellenar todos los campos",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this,
                            "Rellenar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,
                        "Rellenar todos los campos",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,
                    "Rellenar todos los campos",
                    Toast.LENGTH_SHORT).show();
        }


        return false;
    }

    //Método para obtener la edad y luego llamar la función durninMethod
    private void fatPercent(final String gender) {
        dbRef.child(user)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int tempPedad = Integer.parseInt(dataSnapshot.child("edad").getValue().toString());

                            //Llamado a la función durninMethod() para calcular el porcentaje de grasa
                            durninMethod(tempPedad, gender, suma);
                            fat.setText(decimalFormat.format(porcentajeGrasa));
                            setResults(porcentajeGrasa);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    //Obtiene el género del cliente y luego llamar la función fatPercent
    private void getGenero() {
        dbRef.child(user)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String genero = snapshot.child("genero").getValue().toString();
                            //Llamo al método para obtener el porcentaje de grasa, enviando como parametro el género del cliente
                            fatPercent(genero);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    //Función para calcular la dencidad corporal y a partir de allí, el porcentaje de grasa
    private void durninMethod(int edad, String gender, double pliegues ){
        //Formula Durnin/Womersley para 4 pliegues
        //Calculo de dencidad corporal
        double dc = 0;
        if(gender.equals("Hombre")){
            if(edad>16 && edad<=19){
                dc = HC1 - (HM1*Math.log10(pliegues));
            }else if(edad>19 && edad<=29){
                dc = HC2 - (HM2*Math.log10(pliegues));
            }else if(edad>29 && edad<=39){
                dc = HC3 - (HM3*Math.log10(pliegues));
            }else if(edad>39 && edad<=49){
                dc = HC4 - (HM4*Math.log10(pliegues));
            }else if(edad >=50){
                dc = HC5 - (HM5*Math.log10(pliegues));
            }
        }else if(gender.equals("Mujer")){
            if(edad>16 && edad<=19){
                dc = MC1 - (MM1*Math.log10(pliegues));
            }else if(edad>19 && edad<=29){
                dc = MC2 - (MM2*Math.log10(pliegues));
            }else if(edad>29 && edad<=39){
                dc = MC3 - (MM3*Math.log10(pliegues));
            }else if(edad>39 && edad<=49){
                dc = MC4 - (MM4*Math.log10(pliegues));
            }else if(edad >=50){
                dc = MC5 - (MM5*Math.log10(pliegues));
            }
        }

        //Formula Siri
        //Calculo de porcentaje de grasa

        porcentajeGrasa = ((4.95/dc)-4.5)*100;

    }

    private void setResults(double tempGrasPercent){
        //Título del reporte
        String tempWeek = "Semana # "+week.getText().toString();

        //Editar textos con los resultados
        weekTitle.setText(tempWeek);
        fat.setText(decimalFormat.format(porcentajeGrasa));

        //Cálculo de la masa grasa
        double tempPeso = Double.parseDouble(peso.getText().toString().trim());
        double tempGrasa = Double.parseDouble(decimalFormat.format(tempGrasPercent));
        double tempMasaGrasa = (tempPeso*tempGrasa)/100;
        fatMass.setText(decimalFormat.format(tempMasaGrasa));

        //Cálculo de la masa magra
        double tempMasaMagra = tempPeso-tempMasaGrasa;
        leanMass.setText(decimalFormat.format(tempMasaMagra));

        //Cálculo del metabolismo en reposo
        double tempMetabolism = (tempMasaMagra*21.6)+370;
        metabolism.setText(decimalFormat.format(tempMetabolism));

    }

    public void saveClick(View view){
        saveData();
    }

    private void saveData(){
        progressData data = new progressData(pantorrillaIzq.getText().toString().trim(), pantorrillaDer.getText().toString().trim(),
                cuadricepsIzq.getText().toString().trim(), cuadricepsDer.getText().toString().trim(), gluteos.getText().toString().trim(),
                cadera.getText().toString().trim(), abdomen.getText().toString().trim(), espalda.getText().toString().trim(),
                brazoIzq.getText().toString().trim(), brazoDer.getText().toString().trim(), antebrazoIzq.getText().toString().trim(),
                antebrazoDer.getText().toString().trim(), peso.getText().toString().trim(), tricipital.getText().toString().trim(),
                bicipital.getText().toString().trim(), subescapular.getText().toString().trim(), suprailiaco.getText().toString().trim(),
                fat.getText().toString().trim(),week.getText().toString().trim(), fatMass.getText().toString().trim(),
                leanMass.getText().toString().trim(), metabolism.getText().toString().trim(), user);

        dbRef2.child(user+"Week"+week.getText().toString())
                .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(addProgress.this, "Progreso Guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}