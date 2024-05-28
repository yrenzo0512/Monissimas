package com.example.diseno2.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.diseno2.Interface.ChangeNumberItemsListener;
import com.example.diseno2.Models.Producto;

import java.util.ArrayList;
import java.util.List;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;



    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);

    }
    public void insertFood(Producto item) {
        ArrayList<Producto> listaProducto = ObtenerCarrito();
        boolean existAlready = false;
        int index = -1;

        // Verificar si el producto ya existe en el carrito
        for (int i = 0; i < listaProducto.size(); i++) {
            if (listaProducto.get(i).getNombreproducto().equals(item.getNombreproducto())) {
                existAlready = true;
                index = i;
                break;
            }
        }

        if (existAlready) {
            // Si el producto ya existe, actualizar la cantidad
            listaProducto.get(index).setNumberInCart(item.getNumberInCart());
        } else {
            // Si el producto no existe, agregarlo al carrito
            listaProducto.add(item);
        }

        // Guardar la lista actualizada en SharedPreferences (o en tu método preferido)
        tinyDB.putListObject("CardList", listaProducto);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<Producto> ObtenerCarrito(){
        return tinyDB.getListObject("CardList");
    }
    public void minusNumberFood(ArrayList<Producto> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if (listFood.get(position).getNumberInCart()==1){
            listFood.remove(position);
        }else{
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() -1);
        }
        tinyDB.putListObject("CardList", listFood);
        changeNumberItemsListener.changed();
    }
    public void plusNumberFood(ArrayList<Producto> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() +1);
        tinyDB.putListObject("CardList", listFood);
        changeNumberItemsListener.changed();
    }
    public Double getTotalFee() {
        List<Producto> listFood2 = ObtenerCarrito();
        double fee = 0;
        for (int i = 0; i < listFood2.size(); i++) {
            fee = fee + (listFood2.get(i).getPrecionormal() * listFood2.get(i).getNumberInCart());
        }
        return fee;
    }

    public void limpiarCarrito() {
        // Supongamos que 'productosEnCarrito' es la lista que contiene los productos en el carrito
        ArrayList<Producto> listaprducto = ObtenerCarrito();

        listaprducto.clear();

        tinyDB.putListObject("Cardist", listaprducto);


        Toast.makeText(context, "Carrito Limpio", Toast.LENGTH_SHORT).show();

    }

}
