package com.andres.personaltrainer.addClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.andres.personaltrainer.R;

public class personalData2 extends AppCompatActivity {

    //windows to change the statusbar color
    private Window window;

    //EditTexts
    private EditText phone, age, date;

    //Strings
    String name, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data2);

        initViews();
    }

    private void initViews(){
        //EditTexts
        age = findViewById(R.id.ageTxt);
        phone = findViewById(R.id.phoneTxt);
        date = findViewById(R.id.dateTxt);

        //windows to change the statusbar color
        this.window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FF4949"));

        //Strings
        name = getIntent().getStringExtra("nombre");
        lastName = getIntent().getStringExtra("apellido");
    }



    private void goNext(String edad, String cel, String fecha, String name, String lastname){
        Intent intent = new Intent(this, personalData3.class);
        intent.putExtra("celular", cel);
        intent.putExtra("fecha", fecha);
        intent.putExtra("edad", edad);
        intent.putExtra("nombre", name);
        intent.putExtra("apellido", lastname);
        startActivity(intent);
    }

    private boolean check(){
        if(!age.getText().toString().isEmpty()){
            if(Integer.parseInt(age.getText().toString())>16){
                if(!phone.getText().toString().isEmpty()){
                    if(!date.getText().toString().isEmpty()){
                        //All checked
                        return true;

                    }else{
                        Toast.makeText(this,
                                "Rellenar todos los campos",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,
                            "Rellenar todos los campos",
                            Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,
                        "La edad debe ser mayor a 16",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,
                    "Rellenar todos los campos",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void nextClick(View view){
        if(check()){
            goNext(age.getText().toString(), phone.getText().toString(), date.getText().toString(),
                    name, lastName);
        }
    }
}