package app.crud.firebase.crudandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.crud.firebase.crudandroid.Model.Cliente;
import app.crud.firebase.crudandroid.Model.Producto;

public class modificaCliente extends AppCompatActivity {

    EditText nombrec,apellidoc,correoc,edadc;
    Button btn_modificar,btn_eliminar;

    String nombre,apellido,correo,edad,id;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_cliente);

        inicializarFirebase();

        Bundle b = getIntent().getExtras();
        if(b!=null){
            id = b.getString("id");
            nombre = b.getString("nombre");
            apellido = b.getString("Apellido");
            correo = b.getString("Correo");
            edad = b.getString("Edad");
        }

        nombrec = findViewById(R.id.txt_nombrec);
        apellidoc = findViewById(R.id.txt_apellido);
        correoc = findViewById(R.id.txt_correo);
        edadc = findViewById(R.id.txt_edad);

        nombrec.setText(nombre);
        apellidoc.setText(apellido);
        correoc.setText(correo);
        edadc.setText(edad);

        btn_modificar = (Button) findViewById(R.id.btnModificarCliente);
        btn_eliminar = (Button) findViewById(R.id.btnEliminar);

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
                onBackPressed();
            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar();
                onBackPressed();
            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void Modificar(){



        Cliente p = new Cliente();
        p.setId(id);
        p.setNombre(nombrec.getText().toString().trim());
        p.setApellido(apellidoc.getText().toString().trim());
        p.setCorreo(correoc.getText().toString().trim());
        p.setEdad(edadc.getText().toString().trim());
        databaseReference.child("Cliente").child(p.getId()).setValue(p);
        Toast.makeText(this,"Modificado", Toast.LENGTH_SHORT).show();

    }

    private void Eliminar(){
        Cliente p = new Cliente();
        p.setId(id);
        databaseReference.child("Cliente").child(p.getId()).removeValue();
        Toast.makeText(this,"Eliminado", Toast.LENGTH_SHORT).show();
    }
}
