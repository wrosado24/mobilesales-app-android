package com.example.mobilesales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesales.api.ComprasApi;
import com.example.mobilesales.model.Compras;
import com.example.mobilesales.model.Productos;
import com.example.mobilesales.model.Session;
import com.example.mobilesales.ui.dashboard.DashboardFragment;
import com.example.mobilesales.utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Compra extends AppCompatActivity {

    Productos productos;
    TextView nombre, precio, cantidad, pago;
    Double total;
    Integer cant;
    Button bttn_confirmar;
    ComprasApi comprasApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        nombre = findViewById(R.id.nombre_compra);
        precio = findViewById(R.id.precio_compra);
        cantidad = findViewById(R.id.cantidad_compra);
        pago = findViewById(R.id.pago_total);
        bttn_confirmar = findViewById(R.id.bttn_confirmar);
        comprasApi = ApiClient.getClient().create(ComprasApi.class);

        bttn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llenar_compra();
            }
        });
        getSupportActionBar().hide();

        //Recibiendo el producto con todas sus caracterisiticas
        Bundle miBundle=getIntent().getExtras();
        try{
            productos = (Productos) miBundle.getSerializable("productos");
            cant = miBundle.getInt("cantidad");
            nombre.setText(productos.getNombre());
            precio.setText(String.valueOf(productos.getPrecio()));
            cantidad.setText(String.valueOf(cant));
            total = productos.getPrecio() * cant;
            pago.setText(String.valueOf(total));
        }catch (Exception e){
            Toast.makeText(Compra.this, e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    public void llenar_compra(){
        Compras compras_exitosa = new Compras(Session.token_logeo, "string", productos.getNombre(), productos.getMarca(), Double.valueOf(cant), productos.getPrecio(), total);
        Call<Compras> call = comprasApi.guardarCompra(compras_exitosa);
        call.enqueue(new Callback<Compras>() {
            @Override
            public void onResponse(Call<Compras> call, Response<Compras> response) {
                Toast.makeText(getApplicationContext(),"Compra exitosa.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Compras> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
