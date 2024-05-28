package com.example.diseno2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.diseno2.R;

public class InicioCitas extends AppCompatActivity {

    Context contexto;
    Button btnListar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_citas);
        bottomNavigation();
        iniciar();
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
                startActivity(new Intent(InicioCitas.this,IntroActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioCitas.this,CartActivity.class));
            }
        });
        btnservicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioCitas.this,ServicioActivity.class));
            }
        });
        citasBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioCitas.this,InicioCitas.class));
            }
        });
        BtnAjustes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioCitas.this,ConfiguracionActivity.class));
            }
        });
    }
    private void iniciar() {

        contexto = getApplicationContext(); // cargamos el  contexto de la app actualmente y se almacena en la variable contexto
        //el botón con el identificador BTNListarLibros, se almacena en la variable btnListar.
        btnRegistrar = findViewById(R.id.BTNRegistrar);
        btnListar = findViewById(R.id.BTNListar);
    }

    public void SaltarRegistrar(View vista) {

        Toast.makeText(contexto, "Registrar", Toast.LENGTH_SHORT).show();
        //Se crea un nuevo objeto intent llamado in1.....cuando se ejecute el intent, la aplicación cambiará a la actividad representada por la clase
        Intent in1 = new Intent(contexto, CitasActivity.class);

        Bundle bolsita = new Bundle();
        bolsita.putInt("id",0);
        in1.putExtras(bolsita);

        startActivity(in1);
    }

    public void SaltarListar(View vista) {
        Toast.makeText(contexto, "Listar", Toast.LENGTH_SHORT).show();
        Intent in2 = new Intent(contexto, HistorialCitas.class);
        startActivity(in2);
    }
}