package com.example.diseno2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.diseno2.Portada;
import com.example.diseno2.R;
import com.example.diseno2.Registrar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConfiguracionActivity extends AppCompatActivity {

    FloatingActionButton ajustes, agregarUsuario;
    Boolean visible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        bottomNavigation();
        bottonCerrarSesion();
        ajustes = (FloatingActionButton) findViewById(R.id.AJUSTES);
        agregarUsuario = (FloatingActionButton) findViewById(R.id.FABAgregarUsuario);
        agregarUsuario.setVisibility(View.GONE); // no es visible en pantalla y no ocupa espacio


        //escuchando al FAB principal
        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visible) {
                    agregarUsuario.show();
                    visible = false;
                } else {
                    agregarUsuario.hide();
                    visible = true;

                }

            }
        });

        // escuchando al FAB de agregar Usuario
        agregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vistaSaltar = new Intent(getApplicationContext(), Registrar.class);
                startActivity(vistaSaltar);
            }
        });

    }
    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout btnservicio = findViewById(R.id.btnservicio);
        LinearLayout citasBtn = findViewById(R.id.citasBtn);
        LinearLayout BtnAjustes = findViewById(R.id.BtnAjustes);

        homeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracionActivity.this,IntroActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracionActivity.this,CartActivity.class));
            }
        });
        btnservicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracionActivity.this,ServicioActivity.class));
            }
        });
        citasBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracionActivity.this,CitasActivity.class));
            }
        });
        BtnAjustes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracionActivity.this,ConfiguracionActivity.class));
            }
        });
    }
    private void bottonCerrarSesion() {
        Button BTNRegistrar = findViewById(R.id.BTNRegistrar);
        BTNRegistrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfiguracionActivity.this, Portada.class));
            }
        });
    }
}