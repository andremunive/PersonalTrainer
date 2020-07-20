package com.andres.personaltrainer.customersView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;

import com.andres.personaltrainer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class customers extends AppCompatActivity {

    private RecyclerView customersRecView;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("clientes");
    private ArrayList<customer> customerArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        //Init views
        customersRecView = findViewById(R.id.customersRecView);

        customersList();
    }

    public void customersList(){
        Query query = dbRef.orderByChild("estado").equalTo("Activo");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                customerArrayList.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    //Obteniendo nombre de la base de datos
                    String nombre = snapshot.getValue().toString()
                            .split(",")[8].split("=")[1];
                    nombre = nombre .substring(0, nombre.length()-1);

                    //Obteniendo apellido de la base de datos
                    String apellido = snapshot.getValue().toString()
                            .split(",")[0].split("=")[1];

                    //Obteniendo la edad de la base de datos
                    String edad = snapshot.getValue().toString()
                            .split(",")[6].split("=")[1];

                    String completeName = nombre+" "+apellido;

                    //El correo es el 4
                    String user = snapshot.getValue().toString()
                            .split(",")[4].split("@")[0]
                            .split("=")[1];


                    System.out.println("Obtenido: "+nombre+" "+apellido);

                    customerArrayList.add(new customer(completeName, user, edad));
                    customersRecViewAdapter adapter = new customersRecViewAdapter(customers.this);
                    adapter.setCustomerArrayList(customerArrayList);

                    customersRecView.setAdapter(adapter);
                    customersRecView.setLayoutManager(new LinearLayoutManager(customers.this));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });
    }

}