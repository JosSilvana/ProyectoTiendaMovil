package com.uisrael;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RegistrarUsuario extends AppCompatActivity {


    EditText etNombre, etApellido, etContraseña, etRContraseña, etUsername;
    String passwordEncriptacion = "gdsawr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        etNombre = findViewById(R.id.etnombre);
        etApellido = findViewById(R.id.etapellido);
        etUsername = findViewById(R.id.etusuario);
        etContraseña = findViewById(R.id.etcontrasenia);
        etRContraseña = findViewById(R.id.etrcontrasenia);
    }

    public void ventanaIniciarSesion(View v){
        Intent intentEnvio = new Intent(this, IniciarSesion.class);
        startActivity(intentEnvio);
    }

    public boolean verificarContraseña(String contraseña, String rcontraseña) {
        if(contraseña.equals(rcontraseña)==true){
           return true;
        }else{
            Toast.makeText(getApplicationContext(),"¡Las contraseñas no coinciden!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean validarCampos(String nombre, String apellido,String username,String contrasenia, String  rcontrasenia){
        boolean valor= true;
        if(nombre.isEmpty()){
            etNombre.setError("No ha ingresado el nombre");
            valor=false;
        }
        if(apellido.isEmpty()){
            etApellido.setError("No ha ingresado el apellido");
            valor=false;
        }
        if(username.isEmpty()){
            etUsername.setError("No ha ingresado el username");
            valor=false;
        }
        if(contrasenia.isEmpty()){
            etContraseña.setError("No ha ingresado la contraseña");
            valor=false;
        }
        if(rcontrasenia.isEmpty()){
            etRContraseña.setError("No ha ingresado la confirmación de contraseña");
            valor=false;
        }
        return valor;
    }

    private String encriptar(String datos, String password) throws Exception{
        SecretKeySpec secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
        String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
        return datosEncriptadosString;
    }
    private SecretKeySpec generateKey(String password) throws Exception{
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }

    public void LimpiarDatos() {
        etNombre.setText("");
        etApellido.setText("");
        etUsername.setText("");
        etContraseña.setText("");
        etRContraseña.setText("");
    }

    public void Registrar(View v) throws Exception {
        String nombre= etNombre.getText().toString().trim();
        String apellido= etApellido.getText().toString().trim();
        String username= etUsername.getText().toString().trim();
        String contrasenia= etContraseña.getText().toString().trim();
        String rcontrasenia= etRContraseña.getText().toString().trim();

        if (validarCampos(nombre, apellido,username,contrasenia, rcontrasenia)== true){
            if(verificarContraseña(contrasenia,rcontrasenia)==true){
               String ValContraseña= encriptar(contrasenia, passwordEncriptacion);
                postUsuario servicioTask= new postUsuario(this,"http://192.168.100.42/RestPryMovil/postUsuario.php",nombre,apellido, username,ValContraseña);
                servicioTask.execute();
                Intent intentEnvio = new Intent(this, IniciarSesion.class);
                LimpiarDatos();
                startActivity(intentEnvio);
            }
        }
    }
}
