package com.example.diseno2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseno2.Activity.IntroActivity;
import com.example.diseno2.ConexionApi.ConexionApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText txtUsername, txtPass;
    private Context context;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //interfas
        setContentView(R.layout.activity_login);
        iniciarLoginActivity();
    }
    //Un método para inicializar elementos relacionados con la interfaz de usuario y la comunicación con el servidor
    private void iniciarLoginActivity(){
        txtUsername = findViewById (R.id.txtLogin);
        txtPass = findViewById(R.id.txtPassword);

        context = getApplicationContext();

        requestQueue = Volley.newRequestQueue(context);
    }
    //Realiza una solicitud GET utilizando la biblioteca Volley para comunicarse con el servidor
    public void iniciarSesion(View vista) {

        String usuario = txtUsername.getText().toString().trim();
        String contra = txtPass.getText().toString().trim();

        String url = ConexionApi.URL_BASE + "usuarios/" + usuario + "&" + contra;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    //procesa la respuesta del servidor y se realiza la navegación a otra actividad
                    @Override
                    public void onResponse(JSONObject response) {
                        String usuarioBd;
                        try {
                            String valor = response.get("Detalle").toString();
                            JSONArray arreglo = new JSONArray(valor);
                            JSONObject objeto = new JSONObject(arreglo.get(0).toString());

                            usuarioBd = objeto.getString("usuario");
                            String idUsuario = objeto.getString("ID_USUARIO");
                            guardarPreferencias(idUsuario);

                            Intent pasarMensaje = new Intent(context,IntroActivity.class);
                            //activamos el  Intent
                            startActivity(pasarMensaje);


                            Toast.makeText(getApplicationContext(), "Bienvenido " + usuarioBd, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            txtPass.setText(e.getMessage());
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    //Se utiliza para manejar errores en la respuesta de la solicitud de red.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error en la red", Toast.LENGTH_SHORT).show();
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
        requestQueue.add(jsonObjectRequest);
    }
    private void guardarPreferencias(String idUsuario){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idUsuario", idUsuario);


        editor.commit();
    }

    private void cargarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String clieIdRecibido = preferences.getString("idUsuario", "No existe la información");
    }
}