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
import com.example.diseno2.Activity.HistorialCitas;
import com.example.diseno2.Activity.ServicioActivity;
import com.example.diseno2.Models.Cita;
import com.example.diseno2.Models.Servicio;
import com.example.diseno2.R;

import java.util.List;

public class CitasAdapterRecycler extends RecyclerView.Adapter<CitasAdapterRecycler.ViewHolder>  {
    Context context;
    List<Cita> citas;

    public CitasAdapterRecycler(HistorialCitas historialCitas, List<Cita> citas) {
        this.citas = citas;
    }

    @NonNull
    @Override
    public CitasAdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_citas, parent, false);
        context = parent.getContext();
        return new CitasAdapterRecycler.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CitasAdapterRecycler.ViewHolder holder, int position) {
        holder.txtIdUsuario.setText(citas.get(position).getID_USUARIO());
        holder.txtAptDate.setText(citas.get(position).getAptDate());
        holder.txtAptTime.setText(citas.get(position).getAptTime());
        holder.txtIdServicio.setText(citas.get(position).getID_SERVICIO());
        holder.txtObservacion.setText(citas.get(position).getObservacion());
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdUsuario, txtAptDate, txtAptTime, txtIdServicio, txtObservacion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdUsuario = itemView.findViewById(R.id.txtIdUsuario);
            txtAptDate = itemView.findViewById(R.id.txtAptDate);
            txtAptTime = itemView.findViewById(R.id.txtAptTime);
            txtIdServicio = itemView.findViewById(R.id.txtIdServicio);
            txtObservacion = itemView.findViewById(R.id.txtObservacion);
        }
    }
}

