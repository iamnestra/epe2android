package app.crud.firebase.crudandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    ImageView ima_producto,ima_buscar,ima_cliente,ima_buscarc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ima_buscar = (ImageView) findViewById(R.id.ima_Listar);
        ima_producto = (ImageView) findViewById(R.id.ima_addP);
        ima_cliente = (ImageView) findViewById(R.id.img_cliente);
        ima_buscarc = (ImageView) findViewById(R.id.img_buscar_cliente);


        ima_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,ListaProductos.class);
                startActivity(intent);

            }
        });

        ima_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,AgregarProducto.class);
                startActivity(intent);

            }
        });

        ima_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,AgregarCliente.class);
                startActivity(intent);

            }
        });

        ima_buscarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,ListaClientes.class);
                startActivity(intent);

            }
        });




    }
}
