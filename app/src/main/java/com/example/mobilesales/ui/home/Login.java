package com.example.mobilesales.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesales.MainActivity;
import com.example.mobilesales.R;
import com.example.mobilesales.api.SessionApi;
import com.example.mobilesales.api.UsuarioApi;
import com.example.mobilesales.model.Session;
import com.example.mobilesales.model.Usuario;
import com.example.mobilesales.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    SessionApi sessionApi;
    UsuarioApi usuarioApi;
    Button bttn_logeo;
    TextView emailI, contraseñaI, sin_registrar_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionApi = ApiClient.getClient().create(SessionApi.class);
        usuarioApi = ApiClient.getClient().create(UsuarioApi.class);
        emailI = (TextView) findViewById(R.id.emailTxt);
        sin_registrar_t = (TextView) findViewById(R.id.sin_registrar);
        contraseñaI = (TextView) findViewById(R.id.contraseñaTxt);
        bttn_logeo = findViewById(R.id.bttn_login);
        bttn_logeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(emailI.getText().toString(), contraseñaI.getText().toString(), 1);
            }
        });
        sin_registrar_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, registro.class);
                startActivity(intent);
            }
        });
    }

    public void login(final String email, final String contraseña, final Integer rol){
        Call<Boolean> call = sessionApi.sessionOK(email, contraseña, rol);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() == true){
                    messageValidate(true);
                    obtenerLogeado();//obteniendo datos de las personas logeadas
                }else{
                    messageValidate(false);
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void messageValidate(boolean b){
        if(b == true){
            Toast.makeText(Login.this, "Autenticado correctamente.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Datos incorrectos o vacios.", Toast.LENGTH_SHORT).show();
        }
    }

    public void obtenerLogeado(){
        Call<Usuario> call = usuarioApi.obtenerUsuarioPorEmail(emailI.getText().toString());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario userrr = response.body();
                Session.token_logeo = userrr.getId();
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
