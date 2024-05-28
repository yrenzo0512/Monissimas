package com.example.diseno2.Activity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.diseno2.Adapter.CartListAdapter;
import com.example.diseno2.Helper.ManagementCart;
import com.example.diseno2.Interface.ChangeNumberItemsListener;
import com.example.diseno2.Models.Producto;
import com.example.diseno2.R;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private ShowDetailActivity object;
    private Button btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        bottomNavigation();
        calculateCard();

        object = (ShowDetailActivity) getIntent().getParcelableExtra("object");


        btnComprar = findViewById(R.id.btnComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Producto> productos = managementCart.ObtenerCarrito();

                StringBuilder mensaje = new StringBuilder("Mi pedido:\n");
                for (Producto producto : productos) {
                    mensaje.append(producto.getNombreproducto()).append(": $").append(producto.getPrecionormal()).append("\n");
                }
                mensaje.append("Total: $").append(managementCart.getTotalFee());

                String phone = "+51918575251";

                String url = "https://api.whatsapp.com/send?phone=" + phone + "&text=" + Uri.encode(mensaje.toString());
                try {
                    PackageManager pm = getApplicationContext().getPackageManager();
                    pm.getPackageGids("com.whatsapp",PackageManager.GET_ACTIVITIES);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));

                    startActivity(intent);

                    managementCart.limpiarCarrito();
                    adapter.notifyDataSetChanged();
                }catch(PackageManager.NameNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                }
            }
        });


    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout btnservicio = findViewById(R.id.btnservicio);
        LinearLayout citasBtn = findViewById(R.id.citasBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, IntroActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
        btnservicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, ServicioActivity.class));
            }
        });
        citasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CitasActivity.class));
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        ChangeNumberItemsListener changeNumberItemsListener = () -> {
            calculateCard();
        };
        adapter = new CartListAdapter(this, managementCart.ObtenerCarrito(), changeNumberItemsListener);
        recyclerViewList.setAdapter(adapter);
        if (managementCart.ObtenerCarrito().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
    }

    private void calculateCard() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }



    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        recyclerViewList = findViewById(R.id.view);
        scrollView = findViewById(R.id.scrollView);
    }
}
