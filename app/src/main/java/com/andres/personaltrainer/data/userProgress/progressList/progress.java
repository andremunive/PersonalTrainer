package com.andres.personaltrainer.data.userProgress.progressList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.andres.personaltrainer.R;
import com.andres.personaltrainer.data.userProgress.addNewProgress.addProgress;
import com.andres.personaltrainer.data.userProgress.addNewProgress.personalProgress1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class progress extends AppCompatActivity {

    private TextView userTitle;
    private String usuario;

    private ArrayList<weeks> weeksArrayList = new ArrayList<>();

    private RecyclerView progreso;

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("clientProgress");

    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        usuario = getIntent().getStringExtra("usuario");

        initView();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    public void initView(){
        //TextView
        userTitle = findViewById(R.id.userTitle2);
        userTitle.setText(usuario);

        //RecView
        progreso = findViewById(R.id.progressRecView);

        //ArrayList
        progressList();

    }

    public void addProgressClick(View view){
        showAddProgressView();
    }

    public void showAddProgressView(){
        Intent addIntent = new Intent(this, personalProgress1.class);
        addIntent.putExtra("usuario", usuario);
        startActivity(addIntent);
    }

    private void progressList(){
        Query query = dbRef.orderByChild("user").equalTo(usuario);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                weeksArrayList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String semana = snapshot.getValue().toString()
                            .split(",")[2]
                            .split("=")[1];

                    weeksArrayList.add(new weeks("Semana "+semana, usuario));

                    progressRecViewAdapter adapter = new progressRecViewAdapter(progress.this);
                    adapter.setWeeksArrayList(weeksArrayList);
                    progreso.setAdapter(adapter);
                    progreso.setLayoutManager(new LinearLayoutManager(progress.this));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}