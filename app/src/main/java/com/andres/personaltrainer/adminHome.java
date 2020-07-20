package com.andres.personaltrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.andres.personaltrainer.customersView.customers;

public class adminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    public void addClick(View view){
        startActivity(new Intent(this, addCustomer.class));
    }

    public void customersClick(View view){
        startActivity(new Intent(this, customers.class));
    }

}