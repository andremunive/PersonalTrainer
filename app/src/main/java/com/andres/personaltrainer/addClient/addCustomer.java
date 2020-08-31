package com.andres.personaltrainer.addClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.andres.personaltrainer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addCustomer extends AppCompatActivity {

    private EditText name, lastName, age, phone, date, email, password;
    private RadioButton man, woman;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private ProgressDialog loading;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        initView();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));

    }

    private void initView(){
        //EditTexts
        name = findViewById(R.id.nameTxt);
        lastName = findViewById(R.id.lastNameTxt);
        age = findViewById(R.id.ageTxt);
        phone = findViewById(R.id.phoneTxt);
        date = findViewById(R.id.dateTxt);
        email = findViewById(R.id.emailTxt);
        password = findViewById(R.id.passwordTxt);

        //RadioButtons
        man = findViewById(R.id.manRBtn);
        woman = findViewById(R.id.womanRBtn);

        //ProgressDialog
        loading = new ProgressDialog(this);
    }

    public void signinClick(View view) {
        //Validación de todos los campos
        if (!name.getText().toString().isEmpty()) {
            if (!lastName.getText().toString().isEmpty()) {
                if (!age.getText().toString().isEmpty()) {
                    int edadTemp = Integer.parseInt(age.getText().toString());
                    if (edadTemp > 16) {
                        if (!phone.getText().toString().isEmpty()) {
                            if (!date.getText().toString().isEmpty()) {
                                if (!email.getText().toString().isEmpty()) {
                                    if (!password.getText().toString().isEmpty()) {

                                        //Todos los campos han sido llenados
                                        if (man.isChecked()) {
                                            /*saveData(name.getText().toString().trim(), lastName.getText().toString().trim(),
                                                    age.getText().toString().trim(), phone.getText().toString().trim(),
                                                    "Hombre", date.getText().toString().trim(),
                                                    email.getText().toString().trim(), password.getText().toString().trim());*/
                                        } else if (woman.isChecked()) {
                                            /*saveData(name.getText().toString().trim(), lastName.getText().toString().trim(),
                                                    age.getText().toString().trim(), phone.getText().toString().trim(),
                                                    "Mujer", date.getText().toString().trim(),
                                                    email.getText().toString().trim(), password.getText().toString().trim());*/
                                        }

                                    } else {
                                        Toast.makeText(this,
                                                "Ningún campo puede estar vacío", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this,
                                            "Ningún campo puede estar vacío", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this,
                                        "Ningún campo puede estar vacío", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this,
                                    "Ningún campo puede estar vacío", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this,
                                "La edad debe ser mayor a 16", Toast.LENGTH_SHORT).show();
                    }
                    } else {
                        Toast.makeText(this,
                                "Ningún campo puede estar vacío", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this,
                            "Ningún campo puede estar vacío", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,
                        "Ningún campo puede estar vacío", Toast.LENGTH_SHORT).show();
            }
        }

    /*public void saveData(final String nombre, final String apellido, final String edad, final String tel, final String genero, final String fecha, final String correo, final String clave){
        loading.setTitle("Registrando Usuario...");
        loading.show();
        auth.createUserWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Si el registro con email y contraseña fue exitoso
                        if(task.isSuccessful()){
                            final String userID = correo.split("@")[0];
                            userInfo user = new userInfo(nombre, apellido, edad, tel, genero, fecha, correo, clave, "Activo");
                            dbRef.child("clientes").child(userID).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                loading.dismiss();
                                                Toast.makeText(addCustomer.this,
                                                        "Usuario "+userID+" registrado", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                        }else{
                            Toast.makeText(addCustomer.this,
                                    "Error A000", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/

}