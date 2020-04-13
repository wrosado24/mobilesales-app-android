package com.example.mobilesales.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilesales.R;
import com.example.mobilesales.model.Productos;

import java.util.ArrayList;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolderProductos> implements View.OnClickListener {

    ArrayList<Productos> listaProductos;
    private View.OnClickListener listener;

    public AdaptadorProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
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

    public class ViewHolderProductos extends RecyclerView.ViewHolder{
        TextView nombre, precio;
        ImageView foto;
        public ViewHolderProductos(@NonNull final View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreId);
            precio = (TextView) itemView.findViewById(R.id.precioId);
            foto = itemView.findViewById(R.id.idImagen);
        }
    }

    @NonNull
    @Override
    public ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_productos, null, false);
        view.setOnClickListener(this);
        return new ViewHolderProductos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProductos holder, int position) {
        holder.nombre.setText(listaProductos.get(position).getNombre());
        holder.precio.setText(String.valueOf(listaProductos.get(position).getPrecio()));
        holder.foto.setImageResource(listaProductos.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }
}
