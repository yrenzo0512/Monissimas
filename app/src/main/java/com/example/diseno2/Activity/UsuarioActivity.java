package com.example.diseno2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseno2.Adapter.UsuarioAdapterRecycler;
import com.example.diseno2.ConexionApi.ConexionApi;
import com.example.diseno2.Models.Usuario;
import com.example.diseno2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioActivity extends AppCompatActivity {

    private List<Usuario> usuarios;
    private RequestQueue requestQueue;
    private UsuarioAdapterRecycler usuarioAdapter;
    private RecyclerView rv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        usuarios = new ArrayList<>();
        rv3 = findViewById(R.id.recycle);
        rv3.setLayoutManager(new LinearLayoutManager(this));
        usuarioAdapter = new UsuarioAdapterRecycler(this, usuarios);
        cargarListaUsuarios();
        rv3.setAdapter(usuarioAdapter);
    }
    private void cargarListaUsuarios() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = Uri.parse(ConexionApi.URL_BASE + "/usuarios")
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

                                String idUsuario = objeto.getString("ID_USUARIO");
                                String nombre = objeto.getString("nombres");
                                String correo = objeto.getString("correo");
                                String telef = objeto.getString("telefono");
                                String DNI = objeto.getString("dni");
                                String usuari = objeto.getString("user");
                                String contraseña = objeto.getString("password");
                                // Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();

                                Usuario usuario = new Usuario(idUsuario, nombre, correo, telef, DNI, usuari, contraseña);

                                usuarios.add(usuario);
                                usuarioAdapter.notifyItemRangeInserted(usuarios.size(), 1);
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
}