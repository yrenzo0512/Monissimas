package com.example.diseno2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diseno2.Models.Servicio;

import java.util.List;

public class ServicioAdapterSpinner extends ArrayAdapter<Servicio> {
    public ServicioAdapterSpinner(Context context, List<Servicio> servicios) {
        super(context, android.R.layout.simple_spinner_item, servicios);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        Servicio servicio = getItem(position);

        // Aquí seleccionas el atributo que deseas mostrar en el Spinner
        String textoAMostrar = servicio.getNombreservicio();

        // Estableces el texto en el elemento de la vista
        ((TextView) convertView).setText(textoAMostrar);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
