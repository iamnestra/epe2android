package app.crud.firebase.crudandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.crud.firebase.crudandroid.Model.Producto;

public class ListaProductos extends AppCompatActivity {

    ListView lis_lista;
    private List<Producto> listproducto =  new ArrayList<Producto>();
    ArrayAdapter<Producto> arrayAdapterProducto;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Producto productoSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        lis_lista = (ListView) findViewById(R.id.lis_list);


        inicializarFirebase();
        listarDatos();


        lis_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                productoSelected = (Producto) adapterView.getItemAtPosition(i);

                String id = productoSelected.getId();
                String nombre = productoSelected.getNombre();
                String marca = productoSelected.getMarca();
                String precio = productoSelected.getPrecio();
                String categoria = productoSelected.getCategoria();
                Intent intent = new Intent(ListaProductos.this,modificaProducto.class);
                intent.putExtra("id",id);
                intent.putExtra("nombre",nombre);
                intent.putExtra("marca",marca);
                intent.putExtra("precio",precio);
                intent.putExtra("categoria",categoria);
                startActivity(intent);
            }
        });


    }

    private void listarDatos() {

        databaseReference.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listproducto.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Producto p = objSnapshot.getValue(Producto.class);
                    listproducto.add(p);

                    arrayAdapterProducto = new ArrayAdapter<Producto>(ListaProductos.this, android.R.layout.simple_list_item_1, listproducto);
                    lis_lista.setAdapter(arrayAdapterProducto);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
