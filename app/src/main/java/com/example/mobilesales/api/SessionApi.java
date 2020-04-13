package com.example.mobilesales.api;

import com.example.mobilesales.model.Productos;
import com.example.mobilesales.model.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SessionApi {

    @GET("login/.")
    Call<Boolean> sessionOK(@Query("email") String email, @Query("contraseña") String contraseña, @Query("rol") Integer rol);
}
