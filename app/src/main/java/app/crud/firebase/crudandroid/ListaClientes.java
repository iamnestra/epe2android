package app.crud.firebase.crudandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.crud.firebase.crudandroid.Model.Cliente;
import app.crud.firebase.crudandroid.Model.Producto;

public class ListaClientes extends AppCompatActivity {


    ListView lis_lista;
    private List<Cliente> listcliente =  new ArrayList<Cliente>();
    ArrayAdapter<Cliente> arrayAdapterCliente;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Cliente clienteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        lis_lista = (ListView) findViewById(R.id.lis_list);


        inicializarFirebase();
        listarDatos();


        lis_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                clienteSelected = (Cliente) adapterView.getItemAtPosition(i);

                String id = clienteSelected.getId();
                String nombre = clienteSelected.getNombre();
                String apellido = clienteSelected.getApellido();
                String correo = clienteSelected.getCorreo();
                String edad = clienteSelected.getEdad();
                Intent intent = new Intent(ListaClientes.this,modificaCliente.class);
                intent.putExtra("id",id);
                intent.putExtra("nombre",nombre);
                intent.putExtra("Apellido",apellido);
                intent.putExtra("Correo",correo);
                intent.putExtra("Edad",edad);
                startActivity(intent);
            }
        });


    }

    private void listarDatos() {

        databaseReference.child("Cliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listcliente.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Cliente c = objSnapshot.getValue(Cliente.class);
                    listcliente.add(c);

                    arrayAdapterCliente = new ArrayAdapter<Cliente>(ListaClientes.this, android.R.layout.simple_list_item_1, listcliente);
                    lis_lista.setAdapter(arrayAdapterCliente);
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
