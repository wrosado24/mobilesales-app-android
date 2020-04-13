package com.example.mobilesales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesales.model.Productos;

public class Info extends AppCompatActivity {

    TextView titulo, fabricante, stock, descripcion, precio;
    EditText cantidad;
    Button bttn_comprar;
    public Productos productoSeleccionado;
    ImageView im;

    final int[] imgproductos = {R.drawable.huaweip20, R.drawable.samsunga10, R.drawable.samsunga50, R.drawable.huaweip30_pro, R.drawable.iphone7, R.drawable.xiaomi_mi9, R.drawable.iphone8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        titulo = findViewById(R.id.titulo_info);
        fabricante = findViewById(R.id.fabricante_info);
        stock = findViewById(R.id.stock_info);
        precio = findViewById(R.id.precio_info);
        descripcion = findViewById(R.id.descripcion_info);
        cantidad = findViewById(R.id.cantidad_id);
        im = findViewById(R.id.imageView);
        cantidad.setText("1");

        bttn_comprar = findViewById(R.id.bttn_comprar_confirmacion);

        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        productoSeleccionado = null;

        try {
            productoSeleccionado = (Productos) bundle.getSerializable("productos");
            titulo.setText(productoSeleccionado.getNombre());
            fabricante.setText(productoSeleccionado.getMarca());
            stock.setText(String.valueOf(productoSeleccionado.getStock()));
            precio.setText(String.valueOf(productoSeleccionado.getPrecio()));
            descripcion.setText(productoSeleccionado.getDescripcion());
            im.setImageResource(R.drawable.samsunga50);
        }catch (Exception err){
            Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
        }
        bttn_comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Info.this, Compra.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("productos", productoSeleccionado);
                bundle.putInt("cantidad", Integer.parseInt(cantidad.getText().toString()));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
