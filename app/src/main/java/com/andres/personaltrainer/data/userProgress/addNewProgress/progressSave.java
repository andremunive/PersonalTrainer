package com.andres.personaltrainer.data.userProgress.addNewProgress;

import androidx.annotation.NonNull;
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
import com.andres.personaltrainer.data.userProgress.progressList.progress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class progressSave extends AppCompatActivity {

    //Change barr color
    private Window window;

    //Strings from the last view
    private String pantorrillaIzq, pantorrillaDer, cuadricepsIzq, cuadricepsDer, gluteos, date, week, peso,
            abdomen, espalda, brazoIzq, brazoDer, antebrazoIzq, antebrazoDer, tricipital, bicipital, subescapular,
            suprailiaco, user;

    //Views objects
    private EditText grasaC, masaG, masaM, metabolismo;
    private RadioButton man, woman;

    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    //Firebase
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("clientes");
    private DatabaseReference dbRef2 = FirebaseDatabase.getInstance().getReference("clientProgress");

    //Constantes para la formula de Durnin
    private double HC1 = 1.1620, HC2 = 1.1631, HC3 = 1.1422, HC4 = 1.1620, HC5 = 1.1715,
            HM1 = 0.0630, HM2 = 0.0632, HM3 = 0.0544, HM4 = 0.0700, HM5 = 0.0779;

    private double MC1 = 1.1549, MC2 = 1.1599, MC3 = 1.1423, MC4 = 1.1333, MC5 = 1.1339,
            MM1 = 0.0678, MM2 = 0.0717, MM3 = 0.0632, MM4 = 0.0612, MM5 = 0.0645;

    //Porcentaje de grasa final
    private double porcentajeGrasa;
    //Suma de los pliegues
    private double suma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_save);

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
        tricipital = getIntent().getStringExtra("tricipital");
        bicipital = getIntent().getStringExtra("bicipital");
        subescapular = getIntent().getStringExtra("subescapular");
        suprailiaco = getIntent().getStringExtra("suprailiaco");
        user = getIntent().getStringExtra("user");

        //Views objects
        grasaC = findViewById(R.id.fatPercentTxt);
        masaG = findViewById(R.id.fatMassTxt);
        masaM = findViewById(R.id.leanMassTxt);
        metabolismo = findViewById(R.id.metabolismTxt);
        //RadioButtons
        man = findViewById(R.id.manRBtn);
        woman = findViewById(R.id.womanRBtn);

        //Results

    }



    private void calculos() {

        double triceps, biceps, subescapula, suprailiac;

        triceps = Double.parseDouble(tricipital);
        biceps = Double.parseDouble(bicipital);
        subescapula = Double.parseDouble(subescapular);
        suprailiac = Double.parseDouble(suprailiaco);

        //Suma de todos los pliegues
        suma = triceps + biceps + subescapula + suprailiac;
    }

    private void fatPercent(final String gender) {
        dbRef.child(user)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int tempPedad = Integer.parseInt(dataSnapshot.child("edad").getValue().toString());

                            //Llamado a la función durninMethod() para calcular el porcentaje de grasa
                            durninMethod(tempPedad, gender, suma);
                            grasaC.setText(decimalFormat.format(porcentajeGrasa));
                            setResults(porcentajeGrasa);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

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

    public void calcular(View view){

        calculos();
        try{

            if(man.isChecked()){
                fatPercent("Hombre");
            }else if(woman.isChecked()){
                fatPercent("Mujer");
            }else{
                Toast.makeText(this,
                        "Seleccione un género", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
            Toast.makeText(this,
                    "Error al calcular, verifique los datos", Toast.LENGTH_SHORT).show();
        }

    }

    private void setResults(double tempGrasPercent){

        //Editar textos con los resultados
        grasaC.setText(decimalFormat.format(porcentajeGrasa));

        //Cálculo de la masa grasa.getText().toString().trim());
        double tempPeso = Double.parseDouble(peso);
        double tempGrasa = Double.parseDouble(decimalFormat.format(tempGrasPercent));
        double tempMasaGrasa = (tempPeso*tempGrasa)/100;
        masaG.setText(decimalFormat.format(tempMasaGrasa));

        //Cálculo de la masa magra
        double tempMasaMagra = tempPeso-tempMasaGrasa;
        masaM.setText(decimalFormat.format(tempMasaMagra));

        //Cálculo del metabolismo en reposo
        double tempMetabolism = (tempMasaMagra*21.6)+370;
        metabolismo.setText(decimalFormat.format(tempMetabolism));

    }

    private void saveData(){
        progressData data = new progressData(pantorrillaIzq, pantorrillaDer, cuadricepsIzq, cuadricepsDer, gluteos,
                abdomen, espalda, brazoIzq, brazoDer, antebrazoIzq, antebrazoDer, peso, tricipital, bicipital, subescapular,
                suprailiaco, grasaC.getText().toString().trim(), week, masaG.getText().toString().trim(),
                masaM.getText().toString().trim(), metabolismo.getText().toString().trim(), user, date);

        dbRef2.child(user+"Week"+week)
                .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(progressSave.this, "Progreso Guardado", Toast.LENGTH_SHORT).show();
                    goBack();
                }
            }
        });
    }

    public void saveAllClick(View view){
        if(!grasaC.getText().toString().isEmpty()){
            try{
                saveData();
            }catch(Exception e){
                Toast.makeText(this,
                        "Error guardando el progreso, verifique los datos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,
                    "Seleccione calcular", Toast.LENGTH_SHORT).show();
        }
    }

    private void goBack(){
        Intent intent = new Intent(this, progress.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
        finish();
    }


}