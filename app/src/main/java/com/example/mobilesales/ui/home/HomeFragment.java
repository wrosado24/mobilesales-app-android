package com.example.mobilesales.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilesales.Compra;
import com.example.mobilesales.Info;
import com.example.mobilesales.R;
import com.example.mobilesales.adaptador.AdaptadorProductos;
import com.example.mobilesales.api.ProductosApi;
import com.example.mobilesales.api.UsuarioApi;
import com.example.mobilesales.model.Productos;
import com.example.mobilesales.model.Session;
import com.example.mobilesales.model.Usuario;
import com.example.mobilesales.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ProductosApi productosApi;
    UsuarioApi usuarioApi;
    ArrayList<Productos> listaProductos_n;
    RecyclerView recyclerProductos;
    AdaptadorProductos adapter;
    TextView nombre_presentacion;
    final String nombre = "";
    int i = 0;
    Button click_huawei, click_samsung, click_apple, click_xiaomi, click_total;

    //Setear imagenes totales
    final int[] imgproductos = {R.drawable.huaweip20, R.drawable.samsunga10, R.drawable.samsunga50, R.drawable.huaweip30_pro, R.drawable.iphone7, R.drawable.xiaomi_mi9, R.drawable.iphone8};
    final int[] imgHuawei = {R.drawable.huaweip20, R.drawable.huaweip30_pro};
    final int[] imgSamsung = {R.drawable.samsunga10, R.drawable.samsunga50};
    final int[] imgApple = {R.drawable.iphone7, R.drawable.iphone8};
    final int[] imgXiaomi = {R.drawable.xiaomi_mi9};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        listaProductos_n = new ArrayList<>();
        productosApi = ApiClient.getClient().create(ProductosApi.class);
        usuarioApi = ApiClient.getClient().create(UsuarioApi.class);
        recyclerProductos = (RecyclerView) root.findViewById(R.id.RecyclerId);
        nombre_presentacion = root.findViewById(R.id.nombreId);


        click_huawei = root.findViewById(R.id.click_huaweiFilter);
        click_samsung = root.findViewById(R.id.click_samsungFilter);
        click_apple = root.findViewById(R.id.click_appleFilter);
        click_xiaomi = root.findViewById(R.id.click_xiaomiFilter);
        //click_total = root.findViewById(R.id.click_totalFilter);


        recyclerProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        devolverNombre(Session.token_logeo);
        llenarProductos();
        adapter = new AdaptadorProductos(listaProductos_n);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Productos productoSeleccionado = listaProductos_n.get(recyclerProductos.getChildAdapterPosition(view));
                Intent intent = new Intent(getContext(), Info.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("productos", productoSeleccionado);
                bundle.putInt("img",i);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerProductos.setAdapter(adapter);

        //evento de clicks en filtro de inicio
        click_xiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaProductos_n.clear();
                llenarProductosPorMarca("Xiaomi");
            }
        });

        click_samsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaProductos_n.clear();
                llenarProductosPorMarca("Samsung");
            }
        });
        click_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaProductos_n.clear();
                llenarProductosPorMarca("Apple");
            }
        });
        click_huawei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaProductos_n.clear();
                llenarProductosPorMarca("Huawei");
            }
        });
        /*
        click_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaProductos_n.clear();
                llenarProductos();
            }
        });
         */
        return root;

    }

    public void llenarProductos(){
        Call<List<Productos>> call = productosApi.obtenerProductos();
        listaProductos_n.clear();
        call.enqueue(new Callback<List<Productos>>() {
            //setear imagenes para mostrarlas en el recyclerview

            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                if(response.isSuccessful()){
                    List<Productos> productosL = response.body();
                    for(Productos p:productosL){
                        listaProductos_n.add(new Productos(p.getNombre(),p.getMarca(),p.getDescripcion(),p.getPrecio(),p.getStock(),p.getAlias(), imgproductos[i]));
                        i = i + 1 ;
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void llenarProductosPorMarca(final String marca){
        Call<List<Productos>> call = productosApi.obtenerProductosPorMarca(marca);
        listaProductos_n.clear();
        call.enqueue(new Callback<List<Productos>>() {
            int i = 0;
            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                if(response.isSuccessful()){
                    List<Productos> productosL = response.body();
                    for(Productos p:productosL){
                        if(marca == "Huawei"){
                            listaProductos_n.add(new Productos(p.getNombre(),p.getMarca(),p.getDescripcion(),p.getPrecio(),p.getStock(),p.getAlias(), imgHuawei[i]));
                        }else if(marca == "Samsung"){
                            listaProductos_n.add(new Productos(p.getNombre(),p.getMarca(),p.getDescripcion(),p.getPrecio(),p.getStock(),p.getAlias(), imgSamsung[i]));
                        }else if(marca == "Apple"){
                            listaProductos_n.add(new Productos(p.getNombre(),p.getMarca(),p.getDescripcion(),p.getPrecio(),p.getStock(),p.getAlias(), imgApple[i]));
                        }else if(marca == "Xiaomi"){
                            listaProductos_n.add(new Productos(p.getNombre(),p.getMarca(),p.getDescripcion(),p.getPrecio(),p.getStock(),p.getAlias(), imgXiaomi[i]));
                        }
                        i = i + 1;
                    }
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(),marca + ": " + productosL.size() + " productos han sido encontrados.",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void devolverNombre(Integer id){
        Call<Usuario> call = usuarioApi.obtenerUsuarioPorId(id);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                 Usuario us= response.body();
                 nombre_presentacion.setText(us.getNombre());
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}