package com.example.diseno2.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseno2.Adapter.productoAdapterRecycler;
import com.example.diseno2.ConexionApi.ConexionApi;
import com.example.diseno2.Models.Producto;
import com.example.diseno2.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntroActivity extends AppCompatActivity {

    private RecyclerView.Adapter  adapter2;
    private List<Producto> productos;
    private RequestQueue requestQueue;
    private productoAdapterRecycler productoAdapter;
    private RecyclerView rv1;
    DrawerLayout drawerLayout;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        bottomNavigation();
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        productos = new ArrayList<>();
        rv1 = findViewById(R.id.recycletPro);
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productoAdapter = new productoAdapterRecycler(this, productos);
        cargarListaProductos();
        rv1.setAdapter(productoAdapter);
        bottomNavigation();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,  toolbar, R.string.open_nav, R.string.open_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout btnservicio = findViewById(R.id.btnservicio);
        LinearLayout citasBtn = findViewById(R.id.citasBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,IntroActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,CartActivity.class));
            }
        });
        btnservicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,ServicioActivity.class));
            }
        });
        citasBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,CitasActivity.class));
            }
        });
    }

    private void cargarListaProductos() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = Uri.parse(ConexionApi.URL_BASE + "/productos")
                .buildUpon()
                .build().toString();

        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        procesarRespuesta(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error al cargar la lista de productos", Toast.LENGTH_SHORT).show();
                        Log.e("ProductListActivity", "Error en la solicitud: " + error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", ConexionApi.AUTH);
                return params;
            }
        };

        requestQueue.add(requerimiento);
    }

    private void procesarRespuesta(JSONObject response) {
        try {
            int totalRegistros = response.getInt("Total de registros");
            for (int i = 0; i < totalRegistros; i++) {
                String valor = response.get("Detalle").toString();
                JSONArray arreglo = new JSONArray(valor);
                JSONObject objeto = new JSONObject(arreglo.get(i).toString());

                String idProducto = objeto.getString("ID_PRODUCTO");
                String nombrepro = objeto.getString("nombreproducto");
                String descripcion = objeto.getString("descripciones");
                Double precio = objeto.getDouble("precionormal");
                String preciorebaj = objeto.getString("preciorebajado");
                String stock = objeto.getString("stoks");
                String img = objeto.getString("imagen");
                String idcategoria = objeto.getString("ID_CATEGORIA");

                Producto producto = new Producto(idProducto, nombrepro, descripcion, precio, preciorebaj, stock, img, idcategoria);

                productos.add(producto);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        productoAdapter.notifyItemRangeInserted(productos.size(), 1);
                    }
                });
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}