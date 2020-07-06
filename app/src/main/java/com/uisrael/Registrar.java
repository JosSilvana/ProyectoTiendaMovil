package com.uisrael;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    }

    public void ventanaIniciarSesion(View v){
        Intent intentEnvio = new Intent(this, IniciarSesion.class);
        startActivity(intentEnvio);
    }
}
