package com.example.mobilesales.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilesales.R;
import com.example.mobilesales.model.Compras;
import com.example.mobilesales.model.Productos;

import java.util.ArrayList;

public class AdaptadorCompras extends RecyclerView.Adapter<AdaptadorCompras.ViewHolderCompras> implements View.OnClickListener {

    ArrayList<Compras> listaCompra;
    private View.OnClickListener listener;

    public AdaptadorCompras(ArrayList<Compras> listaCompras) {
        this.listaCompra = listaCompras;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderCompras extends RecyclerView.ViewHolder{
        TextView nombre, precio, fecha, cantidad, total;
        ImageView img;
        public ViewHolderCompras(@NonNull final View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreProducto);
            precio = (TextView) itemView.findViewById(R.id.precioId);
            fecha = (TextView) itemView.findViewById(R.id.fechaCompra);
            cantidad = (TextView) itemView.findViewById(R.id.cantidadCompra);
            total = (TextView) itemView.findViewById(R.id.totalCompra);
        }
    }

    @NonNull
    @Override
    public ViewHolderCompras onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_compras, null, false);
        view.setOnClickListener(this);
        return new ViewHolderCompras(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCompras holder, int position) {
        holder.nombre.setText(listaCompra.get(position).getNombre_producto());
        holder.precio.setText(String.valueOf(listaCompra.get(position).getPrecio()));
        holder.fecha.setText(listaCompra.get(position).getFecha_compra());
        holder.cantidad.setText(String.valueOf(listaCompra.get(position).getUnidades()));
        holder.total.setText(String.valueOf(listaCompra.get(position).getTotal()));

    }

    @Override
    public int getItemCount() {
        return listaCompra.size();
    }
}