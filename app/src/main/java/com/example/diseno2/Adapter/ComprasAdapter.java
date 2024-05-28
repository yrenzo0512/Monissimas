package com.example.diseno2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.diseno2.Models.Producto;
import com.example.diseno2.R;

import java.util.ArrayList;

public class ComprasAdapter extends ArrayAdapter<Producto> {

    private Context context;
    private int resource;

    public ComprasAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Producto> productos) {
        super(context, resource, productos);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        // Obtiene el producto actual
        Producto producto = getItem(position);

        // Infla la vista si aún no existe
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Referencias a las vistas dentro del elemento de compra
        TextView nombreProductoTextView = convertView.findViewById(R.id.nombreProductoTextView);
        TextView precioProductoTextView = convertView.findViewById(R.id.precioProductoTextView);

        // Actualiza las vistas con los datos del producto actual
        if (producto != null) {
            nombreProductoTextView.setText(producto.getNombreproducto());
            precioProductoTextView.setText("$" + producto.getPrecionormal());
        }

        return convertView;
    }
}
