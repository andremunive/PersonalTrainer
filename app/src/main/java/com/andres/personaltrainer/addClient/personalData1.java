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

public class personalData1 extends AppCompatActivity {

    //windows to change the statusbar color
    private Window window;

    //EditTexts
    private EditText name, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data1);

        initViews();
    }

    private void initViews(){
        //EditTexts
        name = findViewById(R.id.nameTxt);
        lastName = findViewById(R.id.lastNameTxt);

        //windows to change the statusbar color
        this.window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    public void nextClick(View view){
        if(check()){
            goNext(name.getText().toString(), lastName.getText().toString());
        }
    }

    //Validate all text fields
    private boolean check(){
        if(!name.getText().toString().isEmpty()){
            if(!lastName.getText().toString().isEmpty()){
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
        return false;
    }

    private void goNext(String name, String lastname){
        Intent intent = new Intent(this, personalData2.class);
        intent.putExtra("nombre", name);
        intent.putExtra("apellido", lastname);
        startActivity(intent);
    }
}