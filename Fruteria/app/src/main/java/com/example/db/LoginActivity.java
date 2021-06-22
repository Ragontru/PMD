package com.example.db;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usuarioEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        config();
        setTitle("Login");
    }

    public void config() {
        // Rescatar datos introducidos por el usuario
        usuarioEditText = findViewById(R.id.editTextUsuario);
        passwordEditText = findViewById(R.id.editTextPassword);
    }

    public void loginUsuario(View view) {

        String usuario = usuarioEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(usuario.equals("usuario") && password.equals("1234")){
            // Inicia el main
            Intent intentMain = new Intent(this, MainActivity.class);
            startActivity(intentMain);
        } else {
            Toast.makeText(this, "Usuario y/o contraseña erróneos", Toast.LENGTH_SHORT).show();
        }

    }
}