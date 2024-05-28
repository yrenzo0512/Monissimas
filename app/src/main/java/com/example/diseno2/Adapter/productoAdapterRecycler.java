package com.example.diseno2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.diseno2.Activity.ShowDetailActivity;
import com.example.diseno2.Models.Producto;
import com.example.diseno2.R;

import java.util.List;

public class productoAdapterRecycler extends RecyclerView.Adapter<productoAdapterRecycler.ViewHolder> {
    private Context context;
    private List<Producto> productos;

    public productoAdapterRecycler(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @NonNull
    @Override
    public productoAdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_producto, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull productoAdapterRecycler.ViewHolder holder, int position) {
        Producto producto = productos.get(position);

        holder.nombre.setText(producto.getNombreproducto());
        holder.precio.setText(String.valueOf(producto.getPrecionormal()));
        String imageUrl = producto.getImagen();

        Glide.with(context)
                .load(imageUrl)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.imagenPro);

        holder.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowDetailActivity.class);
            intent.putExtra("object", producto);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio;
        ImageView imagenPro, addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtNombre);
            precio = itemView.findViewById(R.id.txtPrecio);
            imagenPro = itemView.findViewById(R.id.imageProducto);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}


