package com.example.diseno2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.diseno2.Activity.ServicioActivity;
import com.example.diseno2.Models.Servicio;
import com.example.diseno2.R;

import java.util.List;

public class ServicioAdapterRecycler extends RecyclerView.Adapter<ServicioAdapterRecycler.ViewHolder> {

    Context context;
    List<Servicio> servicios;

    public ServicioAdapterRecycler(ServicioActivity servicioActivity, List<Servicio> servicios) {
        this.servicios = servicios;
    }

    @NonNull
    @Override
    public ServicioAdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_servicio, parent, false);
        context = parent.getContext();
        return new ServicioAdapterRecycler.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioAdapterRecycler.ViewHolder holder, int position) {
        holder.nombre.setText(servicios.get(position).getNombreservicio());
        holder.precio.setText(servicios.get(position).getPrecioservicio());
        holder.descripcion.setText(servicios.get(position).getDescripciones());
        String imageUrl = servicios.get(position).getImagen();
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.imagenPro);
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, precio;
        ImageView imagenPro;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombres);
            descripcion = itemView.findViewById(R.id.txtDescripciones);
            precio = itemView.findViewById(R.id.txtPrecios);
            imagenPro = itemView.findViewById(R.id.imageServicio);
        }
    }
}