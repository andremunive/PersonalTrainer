package com.andres.personaltrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andres.personaltrainer.clientView.clientHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.view.Window;
import android.graphics.Color;

public class login extends AppCompatActivity {

    private EditText user, password;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));



        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String tempmail2 = user.getEmail();
            if (tempmail2.equals("andresmunive9906@gmail.com") || tempmail2.equals("Andresmunive9906@gmail.com")) {
                loginAdminChecked();
            } else {
                loginClientChecked();

            }
        }
    }

    private void initViews(){
        user = findViewById(R.id.userTxt);
        password = findViewById(R.id.passwordTxt);

    }

    private boolean check(){

        if(!user.getText().toString().isEmpty()){
            if(!password.getText().toString().isEmpty()){
                return true;
            }else{
                Toast.makeText(this,
                        "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,
                    "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }

        return false;
    }


    public void loginClick(View view){
        if(check()){
            final String tempMail = user.getText().toString().trim() +"@gmail.com";
            auth.signInWithEmailAndPassword(tempMail, password.getText().toString().trim())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                if(tempMail.equals("andresmunive9906@gmail.com") || tempMail.equals("Andresmunive9906@gmail.com")){
                                    loginAdminChecked();
                                }else{
                                    loginClientChecked();
                                }
                                Toast.makeText(login.this, "Bienvenido(a) "+user.getText().toString(),
                                        Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(login.this,
                                        "Verifique usuario y/o contraseña",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void loginAdminChecked(){
        startActivity(new Intent(login.this, adminHome.class));
        finish();
    }

    private void loginClientChecked(){
        Intent intent = new Intent(this, clientHome.class);
        intent.putExtra("Usuario", user.getText().toString().trim());
        startActivity(intent);
        finish();
    }

    public void helpClick(View view){
        Toast.makeText(this,
                "No disponible",
                Toast.LENGTH_SHORT).show();

    }



}
