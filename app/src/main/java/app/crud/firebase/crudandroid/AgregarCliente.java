package app.crud.firebase.crudandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import app.crud.firebase.crudandroid.Model.Cliente;
import app.crud.firebase.crudandroid.Model.Producto;

public class AgregarCliente extends AppCompatActivity {

    EditText nombrec,apellidoc,correoc,edadc;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        nombrec = findViewById(R.id.txt_nombrec);
        apellidoc = findViewById(R.id.txt_apellido);
        correoc = findViewById(R.id.txt_correo);
        edadc = findViewById(R.id.txt_edad);


        inicializarFirebase();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void onClick(View view){
        registrarCliente();
        onBackPressed();

    }

    private void registrarCliente(){


        if(nombrec.length() <=0){
            Toast.makeText(AgregarCliente.this, "Ingrese nombre cliente ", Toast.LENGTH_SHORT).show();
        }else if (apellidoc.length() <=0){
            Toast.makeText(AgregarCliente.this, "Ingrese apellido cliente", Toast.LENGTH_SHORT).show();
        }else if (correoc.length() <=0){
            Toast.makeText(AgregarCliente.this, "Ingrese correo cliente ", Toast.LENGTH_SHORT).show();
        }else if (edadc.length() <=0){
            Toast.makeText(AgregarCliente.this, "Ingrese edad cliente ", Toast.LENGTH_SHORT).show();
        }


        try{


            String nombre = nombrec.getText().toString();
            String apellido = apellidoc.getText().toString();
            String correo = correoc.getText().toString();
            String edad = edadc.getText().toString();


            Cliente c = new Cliente();
            c.setId(UUID.randomUUID().toString());
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setCorreo(correo);
            c.setEdad(edad);


            databaseReference.child("Cliente").child(c.getId()).setValue(c);


        }catch(Exception e){


            Toast.makeText(this,"ERROR:" + e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

}
