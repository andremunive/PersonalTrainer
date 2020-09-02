package com.andres.personaltrainer.data.userProgress.progressList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andres.personaltrainer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class weekProgress extends AppCompatActivity {

    private TextView title;

    private String titulo, usuario;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("clientProgress");

    private EditText pantorrillaIzq, pantorrillaDer, cuadricepsIzq, cuadricepsDer, gluteos, abdomen, espalda,
            brazoIzq, brazoDer, antebrazoIzq, antebrazoDer, peso,
            fat, week, fatMass, leanMass, metabolism;

    private ArrayList<String> data = new ArrayList<>();

    private Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_progress);

        titulo = getIntent().getStringExtra("Semana");
        usuario = getIntent().getStringExtra("Usuario");

        initView();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    private void initView(){
        //TextViews
        title = findViewById(R.id.titleTxt);
        title.setText(titulo);

        //Datos a mostrar
        pantorrillaIzq = findViewById(R.id.pantorrillaIzqTxt);
        pantorrillaDer = findViewById(R.id.pantorrillaDerTxt);
        cuadricepsIzq = findViewById(R.id.cuadricepsIzqTxt);
        cuadricepsDer = findViewById(R.id.cuadricepsDerTxt);
        gluteos = findViewById(R.id.gluteosTxt);
        abdomen = findViewById(R.id.abdomenTxt);
        espalda = findViewById(R.id.espaldaTxt);
        brazoIzq = findViewById(R.id.brazoIzqTxt);
        brazoDer = findViewById(R.id.brazoDerTxt);
        antebrazoDer = findViewById(R.id.antebrazoDerTxt);
        antebrazoIzq = findViewById(R.id.antebrazoIzqTxt);
        peso = findViewById(R.id.pesoTxt);
        fat = findViewById(R.id.fatPercentTxt);
        week = findViewById(R.id.weekTxt);
        fatMass = findViewById(R.id.fatMassTxt);
        leanMass = findViewById(R.id.leanMassTxt);
        metabolism = findViewById(R.id.metabolismTxt);

        searchData();

        //relleno();
    }

    private void searchData(){

        String num = titulo.split(" ")[1];

        Query query = dbRef.orderByChild("user").equalTo(usuario);

        dbRef.child(usuario+"Week"+num).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    data.add(snapshot.getValue().toString());
                }
                //Rellenando los datos por semana, están en el orden de firebase
                if(data.size()>0){
                    pantorrillaIzq.setText(data.get(15));
                    pantorrillaDer.setText(data.get(14));
                    cuadricepsIzq.setText(data.get(6));
                    cuadricepsDer.setText(data.get(5));
                    gluteos.setText(data.get(11));
                    abdomen.setText(data.get(0));
                    espalda.setText(data.get(8));
                    brazoIzq.setText(data.get(4));
                    brazoDer.setText(data.get(3));
                    antebrazoIzq.setText(data.get(2));
                    antebrazoDer.setText(data.get(1));
                    peso.setText(data.get(16));
                    fat.setText(data.get(9));
                    fatMass.setText(data.get(10));
                    leanMass.setText(data.get(12));
                    metabolism.setText(data.get(13));


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





}