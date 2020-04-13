package com.example.mobilesales.api;

import com.example.mobilesales.model.Productos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductosApi {

    @POST("productos/guardar?grantType=value")
    Call<Productos> guardarProductos(@Body Productos productos);

    @GET("productos/list")
    Call<List<Productos>> obtenerProductos();

    @DELETE("productos/{id}")
    Call<Productos> eliminarProducto(@Path("id") Integer id);

    @PUT("productos/.")
    Call<Productos> actualizarProducto(@Body Productos productos);

    @GET("productos/obtenerProductosPorMarca")
    Call<List<Productos>> obtenerProductosPorMarca(@Query("marca") String marca);

}
