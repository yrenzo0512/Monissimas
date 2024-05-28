package com.example.diseno2.Activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diseno2.Adapter.ComprasAdapter;
import com.example.diseno2.Models.Producto;
import com.example.diseno2.R;

import java.util.ArrayList;

public class ComprasActivity extends AppCompatActivity {

    private ListView purchaseListView;
    private ArrayList<Producto> cartProducts;
    private double totalFee, tax, delivery, itemTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        // Obtener datos enviados desde CartActivity
        totalFee = getIntent().getDoubleExtra("totalFee", 0.0);
        tax = getIntent().getDoubleExtra("tax", 0.0);
        delivery = getIntent().getDoubleExtra("delivery", 0.0);
        itemTotal = getIntent().getDoubleExtra("itemTotal", 0.0);

        // Inicializar la lista de compras (puedes obtenerla de la base de datos o la API)
        cartProducts = obtenerListaDeCompras();

        // Configurar el adaptador para la lista de compras
        ComprasAdapter comprasAdapter = new ComprasAdapter(this, R.layout.item_compra, cartProducts);

        // Configurar la vista de lista
        purchaseListView = findViewById(R.id.purchaseListView);
        purchaseListView.setAdapter(comprasAdapter);
    }

    // Método para obtener la lista de compras (puedes modificarlo según tus necesidades)
    private ArrayList<Producto> obtenerListaDeCompras() {
        // Aquí puedes obtener la lista de compras desde la base de datos o la API

        ArrayList<Producto> listaDeCompras = new ArrayList<>();

        return listaDeCompras;
    }
}