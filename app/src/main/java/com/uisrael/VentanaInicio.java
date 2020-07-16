package com.uisrael;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class VentanaInicio extends AppCompatActivity {
    Bundle datoRes;
    private Toolbar toolbar;
    String RecibirNombre, RecibirApellido;
    TextView mostrarUsuario;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_inicio);
        mostrarUsuario = findViewById(R.id.tvUsuario);
        datoRes=getIntent().getExtras();
        RecibirNombre=datoRes.getString("nombre");
        RecibirApellido=datoRes.getString("apellido");
        mostrarUsuario.setText(RecibirNombre+ " "+RecibirApellido);
    }
}
