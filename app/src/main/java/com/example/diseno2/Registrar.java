package com.example.diseno2;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseno2.Activity.IntroActivity;
import com.example.diseno2.ConexionApi.ConexionApi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class Registrar extends AppCompatActivity {
    private EditText usuarioEditText;
    private EditText passwordEditText;
    private EditText nombresEditText;
    private EditText correoEditText;
    private EditText telefonoEditText;
    private EditText dniEditText;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        usuarioEditText = (EditText) findViewById(R.id.txtUsuario);
        nombresEditText = (EditText) findViewById(R.id.txtNombre);
        correoEditText = (EditText) findViewById(R.id.txtCorreo);
        telefonoEditText = (EditText) findViewById(R.id.txtTelefono);
        dniEditText = (EditText) findViewById(R.id.txtDNI);
        passwordEditText = (EditText) findViewById(R.id.txtPassword);

        // instancioando el objeto
        requestQueue = Volley.newRequestQueue(this);
    }
    public void RegistrarDatos(View vista){

        //System.out.println(id.getText().toString());
        //Toast.makeText(this, "registrado correctamente: " + id.getText().toString(), Toast.LENGTH_SHORT).show();

        // Objeto  que recibe como  parametro  5 compomentes (Request.Method.POST, url, new Response.Listener<String>(), new Response.ErrorListener() )
        // y como cuerpo:Map<String, String> getParams() que es la carga de datos a enviar
        String url = Uri.parse(ConexionApi.URL_BASE + "/usuarios")
                .buildUpon()
                .build().toString();

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "registrado correctamente: ", Toast.LENGTH_SHORT).show();
                        Intent pasarMensaje = new Intent(getApplicationContext(), Portada.class);
                        //activamos el  Intent
                        startActivity(pasarMensaje);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error al registrar", Toast.LENGTH_SHORT).show();
                        //System.out.println(error.getMessage());
                    }
                }
        ){
            //cargando los datos a enviar
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<>();

                parametros.put("usuario", usuarioEditText.getText().toString());
                parametros.put("nombres", nombresEditText.getText().toString());
                parametros.put("correo", correoEditText.getText().toString());
                parametros.put("telefono", telefonoEditText.getText().toString());
                parametros.put("dni", dniEditText.getText().toString());
                parametros.put("password", passwordEditText.getText().toString());

                return parametros;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", ConexionApi.AUTH);
                return params;
            }
        };
        requestQueue.add(request);
    }
}