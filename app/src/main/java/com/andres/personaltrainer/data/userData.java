package com.andres.personaltrainer.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.andres.personaltrainer.R;
import com.andres.personaltrainer.data.userProgress.progressList.progress;
import com.andres.personaltrainer.payment.payments;

public class userData extends AppCompatActivity {

    private String usuario;
    private TextView userView;

    private Window window;

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


}