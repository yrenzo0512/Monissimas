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
import com.example.diseno2.Activity.UsuarioActivity;
import com.example.diseno2.Models.Servicio;
import com.example.diseno2.Models.Usuario;
import com.example.diseno2.R;

import java.util.List;

public class UsuarioAdapterRecycler extends RecyclerView.Adapter<UsuarioAdapterRecycler.ViewHolder>{
    Context context;
    List<Usuario> usuarios;

    public UsuarioAdapterRecycler(UsuarioActivity usuarioActivity, List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuarioAdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.enlistar_usuario, parent, false);
        context = parent.getContext();
        return new UsuarioAdapterRecycler.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapterRecycler.ViewHolder holder, int position) {
        holder.nombre.setText(usuarios.get(position).getNombres());
        holder.correo.setText(usuarios.get(position).getCorreo());
        holder.telefono.setText(usuarios.get(position).getTelefono());
        holder.dni.setText(usuarios.get(position).getDni());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, correo, telefono, dni;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombre);
            correo = itemView.findViewById(R.id.txtCorreo);
            telefono = itemView.findViewById(R.id.txtTelefono);
            dni = itemView.findViewById(R.id.txtDNI);
        }
    }
}
