package com.example.mobilesales.api;

import com.example.mobilesales.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioApi {

    @POST("usuarios/guardar?grantType=value")
    Call<Usuario> guardarUsuarios(@Body Usuario usuario);

    @GET("usuarios/list")
    Call<List<Usuario>> usuariosList();

    @DELETE("usuarios/{id}")
    Call<Usuario> eliminarUsuario(@Path("id") Integer id);

    @PUT("usuarios/.")
    Call<Usuario> actualizarUsuario(@Body Usuario usuario);

    @GET("login/.")
    Call<Usuario> login(@Query("email") String email, @Query("contraseña") String contraseña, @Query("rol") Integer rol);

    @GET("usuarios/obtenerUsuarioPorEmail/{email}")
    Call<Usuario> obtenerUsuarioPorEmail(@Path("email") String email);

    @GET("usuarios/obtenerUsuarioPorId/{id}")
    Call<Usuario> obtenerUsuarioPorId(@Path("id") Integer id);

}
