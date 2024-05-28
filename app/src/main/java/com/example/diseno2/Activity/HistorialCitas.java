package com.example.diseno2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseno2.Adapter.CitasAdapterRecycler;
import com.example.diseno2.Adapter.ServicioAdapterRecycler;
import com.example.diseno2.ConexionApi.ConexionApi;
import com.example.diseno2.Models.Cita;
import com.example.diseno2.Models.Servicio;
import com.example.diseno2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class HistorialCitas extends AppCompatActivity {

        private List<Cita> citas;
        private RequestQueue requestQueue;
        private CitasAdapterRecycler citaAdapter;
        private RecyclerView rv5;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_historial_citas);
            citas = new ArrayList<>();
            rv5 = findViewById(R.id.recyclerCitas);
            rv5.setLayoutManager(new LinearLayoutManager(this));
            citaAdapter = new CitasAdapterRecycler(this, citas);
            cargarListaCitas();
            rv5.setAdapter(citaAdapter);
            Button btnRegresar = findViewById(R.id.btnRegresar);
            btnRegresar.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    // Regresar a la actividad principal
                    finish();
                }
            });
        }

        private void cargarListaCitas() {
            requestQueue = Volley.newRequestQueue(getApplicationContext());

            String url = Uri.parse(ConexionApi.URL_BASE + "/citas")
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

                                    String idUsuario = objeto.getString("nombres");
                                    String aptDate = objeto.getString("aptDate");
                                    String aptTime = objeto.getString("aptTime");
                                    String idServicio = objeto.getString("nombreservicio");
                                    String observacion = objeto.getString("observacion");

                                    Cita cita = new Cita(idUsuario, aptDate, aptTime, idServicio, observacion);
                                    citas.add(cita);
                                    citaAdapter.notifyItemRangeInserted(citas.size(), 1);
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
    }