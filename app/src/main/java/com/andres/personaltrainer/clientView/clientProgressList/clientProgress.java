package com.andres.personaltrainer.clientView.clientProgressList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.andres.personaltrainer.R;
import com.andres.personaltrainer.data.userProgress.progressList.progress;
import com.andres.personaltrainer.data.userProgress.progressList.progressRecViewAdapter;
import com.andres.personaltrainer.data.userProgress.progressList.weeks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;

public class clientProgress extends AppCompatActivity {

    private String user;

    private TextView title;

    private ArrayList<weeks> weeksArrayList = new ArrayList<>();

    private RecyclerView progreso;

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("clientProgress");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_progress);

        initViews();
    }

    private void initViews(){
        FirebaseUser usuarioTemp = FirebaseAuth.getInstance().getCurrentUser();
        user = usuarioTemp.getEmail().split("@")[0];

        title = findViewById(R.id.clientTitle2);
        title.setText(user);

        //RecView
        progreso = findViewById(R.id.progressClientRecView);

        //ArrayList
        progressList();
    }

    private void progressList(){
        Query query = dbRef.orderByChild("user").equalTo(user);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                weeksArrayList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    //Objeto de la clase con todos los datos del progreso

                    progressData usuario = snapshot.getValue(progressData.class);


                    String semana = usuario.week;







                    weeksArrayList.add(new weeks("Semana "+semana, user));

                    progressRecViewAdapter adapter = new progressRecViewAdapter(clientProgress.this);
                    adapter.setWeeksArrayList(weeksArrayList);
                    progreso.setAdapter(adapter);
                    progreso.setLayoutManager(new LinearLayoutManager(clientProgress.this));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}