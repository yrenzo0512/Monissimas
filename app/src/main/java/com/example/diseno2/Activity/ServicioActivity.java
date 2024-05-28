package com.example.diseno2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseno2.Adapter.ServicioAdapterRecycler;
import com.example.diseno2.ConexionApi.ConexionApi;
import com.example.diseno2.Models.Servicio;
import com.example.diseno2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioActivity extends AppCompatActivity {

    private List<Servicio> servicios;
    private RequestQueue requestQueue;
    private ServicioAdapterRecycler servicioAdapter;
    private RecyclerView rv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);
        servicios = new ArrayList<>();
        rv2 = findViewById(R.id.recycle);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        servicioAdapter = new ServicioAdapterRecycler(this, servicios);
        cargarListaServicios();
        rv2.setAdapter(servicioAdapter);
        bottomNavigation();
    }
    private void cargarListaServicios() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = Uri.parse(ConexionApi.URL_BASE + "/servicios")
                .buildUpon()
                .build().toString();

        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int totalRegistros = response.getInt("Total de registros");
                            for (int i = 0; i < totalRegistros; i++) {

                                String valor = response.get("Detalle").toString();
                                JSONArray arreglo = new JSONArray(valor);
                                JSONObject objeto = new JSONObject(arreglo.get(i).toString());

                                String idServicio = objeto.getString("ID_SERVICIO");
                                String nombre = objeto.getString("nombreservicio");
                                String descripciones = objeto.getString("precioservicio");
                                String precios = objeto.getString("descripciones");
                                String img = objeto.getString("imagen");
                                // Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();

                                Servicio servicio = new Servicio(idServicio, nombre, descripciones, precios, img);

                                servicios.add(servicio);
                                servicioAdapter.notifyItemRangeInserted(servicios.size(), 1);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", ConexionApi.AUTH);
                return params;
            }
        };
        requestQueue.add(requerimiento);
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
                startActivity(new Intent(ServicioActivity.this,IntroActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServicioActivity.this,CartActivity.class));
            }
        });
        btnservicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServicioActivity.this,ServicioActivity.class));
            }
        });
        citasBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServicioActivity.this,InicioCitas.class));
            }
        });
        BtnAjustes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServicioActivity.this,ConfiguracionActivity.class));
            }
        });
    }
}