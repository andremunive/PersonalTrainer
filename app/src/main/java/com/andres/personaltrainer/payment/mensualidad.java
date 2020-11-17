package com.andres.personaltrainer.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.andres.personaltrainer.R;
import com.andres.personaltrainer.data.userProgress.addNewProgress.progressSave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class mensualidad extends AppCompatActivity {

    private Window window;
    private String usuario;

    private RadioButton contado, plazo;
    private EditText meses, total, fecha, abono;

    //Firebase
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("payments");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensualidad);

        initViews();

        this.window = getWindow();

        window.setStatusBarColor(Color.parseColor("#FF4949"));
    }

    private void initViews(){
        usuario = getIntent().getStringExtra("Usuario");
        contado = findViewById(R.id.contadoBtn);
        plazo = findViewById(R.id.plazoBtn);
        meses = findViewById(R.id.mesesTxt);
        total = findViewById(R.id.totalTxt);
        fecha = findViewById(R.id.dateTxt);
        fecha.setText(fechaActual());
        abono = findViewById(R.id.abonoTxt);
    }

    public void calcularClick(View view){
        if(!meses.getText().toString().isEmpty()) {
            int mesesText = Integer.parseInt(meses.getText().toString());
            if (mesesText == 1) {
                if (contado.isChecked()) {
                    total.setText("60000");
                } else if (plazo.isChecked()) {
                    total.setText("80000");
                } else {
                    Toast.makeText(this,
                            "Seleccione una opción de pago", Toast.LENGTH_SHORT).show();
                }
            } else if (mesesText == 2) {
                if (contado.isChecked()) {
                    total.setText("110000");
                } else if (plazo.isChecked()) {
                    total.setText("140000");
                } else {
                    Toast.makeText(this,
                            "Seleccione una opción de pago", Toast.LENGTH_SHORT).show();
                }
            } else if (mesesText == 3) {
                if (contado.isChecked()) {
                    total.setText("160000");
                } else if (plazo.isChecked()) {
                    total.setText("190000");
                } else {
                    Toast.makeText(this,
                            "Seleccione una opción de pago", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,
                        "Pagos disponibles de 1 a 3 meses solamente", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,
                    "Digite la cantidad de meses a pagar", Toast.LENGTH_SHORT).show();
        }
    }


    private String fechaActual(){
        String fecha = "";
        Calendar calendar = Calendar.getInstance();
        String dia = String.valueOf(calendar.get(Calendar.DATE));
        String mes = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        fecha = dia+"/"+mes+"/"+year;

        return fecha;
    }

    public void saveClick(View view){
        if(!meses.getText().toString().isEmpty() && Integer.parseInt(meses.getText().toString())>0 &&
                Integer.parseInt(meses.getText().toString())<3){
            if(!abono.getText().toString().isEmpty()){
                if(!fecha.getText().toString().isEmpty()){
                 //All ready to save
                    //Calculo de la fecha final
                    String fd;
                    int mes = Integer.parseInt(meses.getText().toString());
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.MONTH, mes);
                    String dia = String.valueOf(c.get(Calendar.DATE));
                    String month = String.valueOf(c.get(Calendar.MONTH)+1);
                    String year = String.valueOf(c.get(Calendar.YEAR));
                    fd = dia+"/"+month+"/"+year;

                    //Calculo del saldo
                    int tt = Integer.parseInt(total.getText().toString());
                    int dio = Integer.parseInt(abono.getText().toString());
                    int saldo = tt - dio;

                    //Guardo todos los datos
                    saveData(fd, ""+saldo);




                }
            }
        }
    }

    private void saveData(String finalDate, String saldo){
        paymentData data = new paymentData(usuario, meses.getText().toString(), fecha.getText().toString(), finalDate,
                abono.getText().toString(), saldo);



        dbRef.child(usuario)
                .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(mensualidad.this, "Pago guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}