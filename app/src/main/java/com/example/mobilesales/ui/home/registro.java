package com.example.mobilesales.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilesales.R;
import com.example.mobilesales.api.UsuarioApi;
import com.example.mobilesales.model.Usuario;
import com.example.mobilesales.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registro extends AppCompatActivity {

    private UsuarioApi usuarioApi;
    private EditText nombre, apellido, correo, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuarioApi = ApiClient.getClient().create(UsuarioApi.class);
        nombre = (EditText)findViewById(R.id.nombre_txt);
        apellido = (EditText)findViewById(R.id.apellido_txt);
        correo = (EditText)findViewById(R.id.e_mail_txt);
        contraseña = (EditText)findViewById(R.id.contraseña_txt);
        getSupportActionBar().hide();
    }

    //metodo para insertar registros
    public void insertarRegistros(View view){
        if(nombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty()||
                correo.getText().toString().isEmpty() || contraseña.getText().toString().isEmpty()){
            Toast.makeText(this,"Todos los datos son obligatorios.",Toast.LENGTH_SHORT).show();
        }else{
            Usuario user = new Usuario(nombre.getText().toString(), apellido.getText().toString(), correo.getText().toString(),
                    contraseña.getText().toString(), 1, 0.0);
            Call<Usuario> call = usuarioApi.guardarUsuarios(user);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Usuario user1 = response.body();
                    Toast.makeText(registro.this,"Se ha creado tu cuenta correctamente",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registro.this, Login.class);
                    startActivity(intent);
                    finish();
                }
                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Ha ocurrido un error al intentar introducir los datos",Toast.LENGTH_SHORT).show();
                    call.cancel();
                }
            });
        }
    }




}


