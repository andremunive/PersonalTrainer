package com.andres.personaltrainer.addClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andres.personaltrainer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class personalData3 extends AppCompatActivity {

    //Strings from last view
    private String nombre, apellido, edad, fecha, celular;

    //View Objects
    private EditText email, password;

    //Firebase
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    //Others
    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data3);

        initViewsObjects();
    }

    private void initViewsObjects(){
        nombre = getIntent().getStringExtra("nombre");
        apellido = getIntent().getStringExtra("apellido");
        edad = getIntent().getStringExtra("edad");
        fecha = getIntent().getStringExtra("fecha");
        celular = getIntent().getStringExtra("celular");

        email = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passwordTxt);

    }

    public void registerClick(View view){
        saveData(nombre, apellido, edad, celular, fecha, email.getText().toString(), password.getText().toString());
    }

    public void saveData(final String nombre, final String apellido, final String edad, final String tel, final String fecha, final String correo, final String clave){
        //loading.setTitle("Registrando Usuario...");
        //loading.show();
        auth.createUserWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Si el registro con email y contraseña fue exitoso
                        if(task.isSuccessful()){
                            final String userID = correo.split("@")[0];
                            userInfo user = new userInfo(nombre, apellido, edad, tel, fecha, correo, clave, "Activo");
                            dbRef.child("clientes").child(userID).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                //loading.dismiss();
                                                Toast.makeText(personalData3.this,
                                                        "Usuario "+userID+" registrado", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                        }else{
                            Toast.makeText(personalData3.this,
                                    "Error A000", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}