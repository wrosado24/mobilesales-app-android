package com.example.mobilesales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilesales.api.UsuarioApi;
import com.example.mobilesales.model.Session;
import com.example.mobilesales.model.Usuario;
import com.example.mobilesales.ui.notifications.NotificationsFragment;
import com.example.mobilesales.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActualizarDatos extends AppCompatActivity {

    UsuarioApi usuarioApi;
    Usuario user;
    EditText nombre, apellido, email, pais, genero;
    Button bttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_datos);
        usuarioApi = ApiClient.getClient().create(UsuarioApi.class);
        obtenerLogeado();
        nombre = findViewById(R.id.nombreTxt);
        apellido = findViewById(R.id.apellidoTxt);
        email = findViewById(R.id.emailTxt);
        pais = findViewById(R.id.paisTxt);
        genero = findViewById(R.id.generoTxt);
        bttn = findViewById(R.id.bttn_actualizar_ahora);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
            }
        });

        getSupportActionBar().hide();
    }

    public void obtenerLogeado(){
        Call<Usuario> call = usuarioApi.obtenerUsuarioPorId(Session.token_logeo);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                user = response.body();
                nombre.setText(user.getNombre());
                apellido.setText(user.getApellido());
                email.setText(user.getEmail());
                pais.setText(user.getPais());
                genero.setText(user.getGenero());
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void actualizarDatos(){
        Usuario usuario = new Usuario();
        usuario.setId(Session.token_logeo);
        usuario.setNombre(nombre.getText().toString());
        usuario.setRol(user.getRol());
        usuario.setDinero(user.getDinero());
        usuario.setContrasena(user.getContrasena());
        usuario.setApellido(apellido.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setPais(pais.getText().toString());
        usuario.setGenero(genero.getText().toString());
        Call<Usuario> call = usuarioApi.actualizarUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Intent intent = new Intent(ActualizarDatos.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Se ha actualizado tus datos correctamente.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
