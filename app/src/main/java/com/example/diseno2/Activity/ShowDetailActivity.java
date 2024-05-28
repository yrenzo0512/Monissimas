package com.example.diseno2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.diseno2.Helper.ManagementCart;
import com.example.diseno2.Models.Producto;
import com.example.diseno2.R;

import java.util.Locale;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, totalPriceTxt, starTxt, caloryTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private Producto object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (Producto) getIntent().getSerializableExtra("object");

        int drawableResource = this.getResources().getIdentifier(object.getImagen(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResource).into(picFood);

        titleTxt.setText(object.getNombreproducto());
        feeTxt.setText("$" + object.getPrecionormal());
        descriptionTxt.setText(object.getDescripciones());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        caloryTxt.setText(object.getID_CATEGORIA() + " Calories");
        starTxt.setText(String.valueOf(object.getStoks()));

        totalPriceTxt.setText("$" + Math.round(numberOrder * object.getPrecionormal()));

        plusBtn.setOnClickListener(v -> {
            numberOrder++;
            updateNumberAndTotalPrice();
        });

        minusBtn.setOnClickListener(v -> {
            if (numberOrder > 1) {
                numberOrder--;
                updateNumberAndTotalPrice();
            }
        });

        addToCartBtn.setOnClickListener(v -> {
            if (managementCart != null) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
                Toast.makeText(this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("ShowDetailActivity", "managementCart is null");
            }
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCardBtn);
        minusBtn = findViewById(R.id.minusCardBtn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        starTxt = findViewById(R.id.starTxt);
        caloryTxt = findViewById(R.id.VicaloriesTxt);
    }

    private void updateNumberAndTotalPrice() {
        numberOrderTxt.setText(String.valueOf(numberOrder));
        totalPriceTxt.setText("$" + Math.round(numberOrder * object.getPrecionormal()));
    }
}

