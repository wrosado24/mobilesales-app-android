package com.example.mobilesales.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilesales.Compra;
import com.example.mobilesales.R;
import com.example.mobilesales.adaptador.AdaptadorCompras;
import com.example.mobilesales.adaptador.AdaptadorProductos;
import com.example.mobilesales.api.ComprasApi;
import com.example.mobilesales.api.ProductosApi;
import com.example.mobilesales.model.Compras;
import com.example.mobilesales.model.Session;
import com.example.mobilesales.utils.ApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    RecyclerView recyclerCompras;
    AdaptadorCompras adaptadorCompras;
    ArrayList<Compras> compras_n;
    ComprasApi comprasApi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        compras_n = new ArrayList<>();
        comprasApi = ApiClient.getClient().create(ComprasApi.class);
        recyclerCompras = (RecyclerView) root.findViewById(R.id.RecyclerId);
        recyclerCompras.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarRecycler(Session.token_logeo);
        adaptadorCompras = new AdaptadorCompras(compras_n);
        recyclerCompras.setAdapter(adaptadorCompras);
        return root;
    }


    public void llenarRecycler(Integer id_cliente){
        Call<List<Compras>> call = comprasApi.obtenerComprasPorCliente(id_cliente);
        call.enqueue(new Callback<List<Compras>>() {
            @Override
            public void onResponse(Call<List<Compras>> call, Response<List<Compras>> response) {
                for(Compras c: response.body()){
                    compras_n.add(new Compras(c.getId_cliente(), c.getNombre_cliente(), c.getNombre_producto(), c.getMarca_producto(), c.getUnidades(), c.getPrecio(), c.getTotal(),  c.getFecha_compra()));
                }
                adaptadorCompras.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Compras>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}