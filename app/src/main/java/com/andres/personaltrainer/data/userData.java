package com.andres.personaltrainer.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.andres.personaltrainer.R;
import com.andres.personaltrainer.data.userProgress.progressList.progress;
import com.andres.personaltrainer.payment.payments;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userData extends AppCompatActivity {

    private String usuario;
    private TextView userView;
    private EditText finalDate, deuda;

    private Window window;

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("payments");
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        usuario = getIntent().getStringExtra("Usuario");

        initView();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    public void initView(){
        //TextView
        userView = findViewById(R.id.userTitle);
        userView.setText(usuario);
        finalDate = findViewById(R.id.fdTxt);
        deuda = findViewById(R.id.deudaTxt);

        setFinalDate();
    }

    public void progressClick(View view){
        Intent progressIntent = new Intent(this, progress.class);
        progressIntent.putExtra("usuario", usuario);
        startActivity(progressIntent);
    }

    public void paymentClick(View view){
        Intent intent = new Intent(this, payments.class);
        intent.putExtra("Usuario", usuario);
        startActivity(intent);
    }

    private void setFinalDate(){

        dbRef.child(usuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for(DataSnapshot snapshotd: snapshot.getChildren()){
                    data.add(snapshot.getValue().toString());
                }

                String fecha = data.get(3).split(",")[2]
                        .split("=")[1];

                String debe = data.get(1).split(",")[4]
                        .split("=")[1];

                finalDate.setText(fecha);
                deuda.setText("$"+debe);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}