package com.andres.personaltrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
    private ProgressDialog loading;


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
            if (tempmail2.equals("andresmunive9906@gmail.com") || tempmail2.equals("Andresmunive9906@gmail.com")
            || tempmail2.equals("rubymanjr@gmail.com") || tempmail2.equals("Rubymanjr@gmail.com")) {
                loginAdminChecked();
            } else {
                loginClientChecked();

            }
        }
    }

    private void initViews(){
        user = findViewById(R.id.userTxt);
        password = findViewById(R.id.passwordTxt);
        loading = new ProgressDialog(this);

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
            loading.setTitle("Iniciando Sesión...");
            loading.show();
            final String tempMail = user.getText().toString().trim() +"@gmail.com";
            auth.signInWithEmailAndPassword(tempMail, password.getText().toString().trim())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                if(tempMail.equals("andresmunive9906@gmail.com") || tempMail.equals("Andresmunive9906@gmail.com")
                                        || tempMail.equals("rubymanjr@gmail.com") || tempMail.equals("Rubymanjr@gmail.com")){
                                    loginAdminChecked();

                                }else{
                                    loginClientChecked();

                                }
                                loading.dismiss();
                                Toast.makeText(login.this, "Bienvenido(a) "+user.getText().toString(),
                                        Toast.LENGTH_SHORT).show();

                            }else{
                                loading.dismiss();
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
