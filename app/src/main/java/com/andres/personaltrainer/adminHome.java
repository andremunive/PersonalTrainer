package com.andres.personaltrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.andres.personaltrainer.customersView.customers;
import com.google.firebase.auth.FirebaseAuth;

public class adminHome extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

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

    public void exitClick(View view){
        auth.signOut();
        showLoginView();
    }

    private void showLoginView(){
        startActivity(new Intent(this, login.class));
        finish();
    }

}