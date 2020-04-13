package com.example.mobilesales.ui.notifications;

import android.app.slice.Slice;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobilesales.ActualizarDatos;
import com.example.mobilesales.MainActivity;
import com.example.mobilesales.R;
import com.example.mobilesales.SplashScreen;
import com.example.mobilesales.api.UsuarioApi;
import com.example.mobilesales.model.Session;
import com.example.mobilesales.model.Usuario;
import com.example.mobilesales.utils.ApiClient;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    TextView nombre, apellido,correo, pais, genero;
    UsuarioApi usuarioApi;
    Button bttn_cerar_sesion, bttn_actualizar, bttn_eliminar_cuenta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        usuarioApi = ApiClient.getClient().create(UsuarioApi.class);
        nombre = root.findViewById(R.id.nombre_cuenta);
        apellido = root.findViewById(R.id.apellido_cuenta);
        correo = root.findViewById(R.id.correo_cuenta);
        pais = root.findViewById(R.id.pais_cuenta);
        genero = root.findViewById(R.id.genero_cuenta);
        bttn_cerar_sesion = root.findViewById(R.id.bttn_cerrar_sesion);
        bttn_actualizar = root.findViewById(R.id.bttn_actualizar_datos);
        bttn_eliminar_cuenta = root.findViewById(R.id.bttn_eliminar_cuenta);

        //Eventos de botones
        bttn_cerar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
                Toast.makeText(getContext(), "Sesión terminada.", Toast.LENGTH_SHORT).show();
            }
        });

        bttn_eliminar_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar_cuenta();
            }
        });

        bttn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
            }
        });

        obtenerLogeado();
        return root;
    }

    public void obtenerLogeado(){
        Call<Usuario> call = usuarioApi.obtenerUsuarioPorId(Session.token_logeo);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario user = response.body();
                nombre.setText(user.getNombre());
                correo.setText(user.getEmail());
                apellido.setText(user.getApellido());
                pais.setText(user.getPais());
                genero.setText(user.getGenero());
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cerrarSesion(){
        Session.token_logeo = null;
        Intent intent = new Intent(getContext(), SplashScreen.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void eliminar_cuenta(){
        Call<Usuario> call = usuarioApi.eliminarUsuario(Session.token_logeo);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Toast.makeText(getActivity(),"Tu cuenta se ha eliminado.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SplashScreen.class);
                startActivity(intent);
                getActivity().finish();
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void actualizarDatos(){
        Intent intent = new Intent(getContext(), ActualizarDatos.class);
        startActivity(intent);
    }
}