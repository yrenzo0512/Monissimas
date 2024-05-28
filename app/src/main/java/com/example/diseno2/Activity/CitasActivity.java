package com.example.diseno2.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.diseno2.Adapter.ServicioAdapterSpinner;
import com.example.diseno2.ConexionApi.ConexionApi;
import com.example.diseno2.Models.Servicio;
import com.example.diseno2.Portada;
import com.example.diseno2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitasActivity  extends AppCompatActivity implements View.OnClickListener {

    private EditText txtObservacion;
    private List<Servicio> servicios;
    private RequestQueue requestQueue;
    private Spinner spinnerServicio;
    private Button bFecha, bHora;
    private EditText eFecha, eHora;
    private int dia, mes, ano, hora, minutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        bFecha = findViewById(R.id.bFecha);
        bHora = findViewById(R.id.bHora);
        eFecha = findViewById(R.id.eFecha);
        eHora = findViewById(R.id.eHora);
        txtObservacion = findViewById(R.id.txtObservacion);
        spinnerServicio = findViewById(R.id.spinnerServicio);
        requestQueue = Volley.newRequestQueue(this);

        cargarListaServicios();

        servicios = new ArrayList<>();
        ServicioAdapterSpinner adapter = new ServicioAdapterSpinner(this, servicios);
        spinnerServicio.setAdapter(adapter);

        spinnerServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Servicio servicioSeleccionado = (Servicio) parentView.getItemAtPosition(position);
                Log.d("CitasActivity", "Servicio seleccionado - ID: " + servicioSeleccionado.getID_SERVICIO() + ", Nombre: " + servicioSeleccionado.getNombreservicio());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle cuando no se selecciona nada
            }
        });

        bFecha.setOnClickListener(this);
        bHora.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == bFecha){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    eFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia,mes, ano);
            datePickerDialog.show();
        }
        if (v == bHora){
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    eHora.setText(hourOfDay+":"+minute);
                }
            },hora, minutos, false);
            timePickerDialog.show();

        }
    }
    public void BTNRegistrar(View vista){

        //System.out.println(id.getText().toString());
        //Toast.makeText(this, "registrado correctamente: " + id.getText().toString(), Toast.LENGTH_SHORT).show();

        String fecha = eFecha.getText().toString();
        String hora = eHora.getText().toString();

        Servicio servicioSeleccionado = (Servicio) spinnerServicio.getSelectedItem();
        // Objeto  que recibe como  parametro  5 compomentes (Request.Method.POST, url, new Response.Listener<String>(), new Response.ErrorListener() )
        // y como cuerpo:Map<String, String> getParams() que es la carga de datos a enviar
        String url = Uri.parse(ConexionApi.URL_BASE + "/citas")
                .buildUpon()
                .build().toString();

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "registrado correctamente: ", Toast.LENGTH_SHORT).show();
                        Intent pasarMensaje = new Intent(getApplicationContext(), IntroActivity.class);
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

                SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
                String clieIdRecibido = preferences.getString("idUsuario", "No existe la información");

                parametros.put("ID_USUARIO", clieIdRecibido);
                parametros.put("aptDate", fecha);
                parametros.put("aptTime", hora);
                parametros.put("observacion", txtObservacion.getText().toString());

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
                                //adapter.notifyItemRangeInserted(servicios.size(), 1);
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