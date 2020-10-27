package com.andres.personaltrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import com.andres.personaltrainer.customersView.customers;
import com.andres.personaltrainer.tools.toolsOptions;
import com.google.firebase.auth.FirebaseAuth;



public class adminHome extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4949")));

    }


    public void customersClick(View view){
        startActivity(new Intent(this, customers.class));
    }

    public void exitClick(View view){
        auth.signOut();
        showLoginView();
        finish();
    }

    private void showLoginView(){
        startActivity(new Intent(this, login.class));
        finish();

    }

    public void toolsClick(View view){
        Intent intent = new Intent(this, toolsOptions.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea salir de Personal Trainer?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }



        return super.onKeyDown(keyCode, event);
    }
}