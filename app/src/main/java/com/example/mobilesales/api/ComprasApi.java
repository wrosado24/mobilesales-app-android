package com.example.mobilesales.api;

import com.example.mobilesales.model.Compras;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ComprasApi {

    @POST("compras/guardar")
    Call<Compras> guardarCompra(@Body Compras compras);

    @GET("compras/list/{id_cliente}")
    Call<List<Compras>> obtenerComprasPorCliente(@Path("id_cliente") Integer id_cliente);


}