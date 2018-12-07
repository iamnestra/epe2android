package app.crud.firebase.crudandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import app.crud.firebase.crudandroid.Model.Producto;

public class AgregarProducto extends AppCompatActivity {

    EditText nombrep,marcap,preciop,categoriap;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);


        nombrep = findViewById(R.id.txt_nombrep);
        marcap = findViewById(R.id.txt_marca);
        preciop = findViewById(R.id.txt_precio);
        categoriap = findViewById(R.id.txt_categoria);


        inicializarFirebase();


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void onClick(View view){
        registrarUsuarios();
        onBackPressed();

    }

    private void registrarUsuarios(){


        if(nombrep.length() <=0){
            Toast.makeText(AgregarProducto.this, "Ingrese nombre producto ", Toast.LENGTH_SHORT).show();
        }else if (marcap.length() <=0){
            Toast.makeText(AgregarProducto.this, "Ingrese marca producto", Toast.LENGTH_SHORT).show();
        }else if (preciop.length() <=0){
            Toast.makeText(AgregarProducto.this, "Ingrese precio producto ", Toast.LENGTH_SHORT).show();
        }else if (categoriap.length() <=0){
            Toast.makeText(AgregarProducto.this, "Ingrese categoria producto ", Toast.LENGTH_SHORT).show();
        }


        try{


            String nombre = nombrep.getText().toString();
            String marca = marcap.getText().toString();
            String precio = preciop.getText().toString();
            String categoria = categoriap.getText().toString();


            Producto p = new Producto();
            p.setId(UUID.randomUUID().toString());
            p.setNombre(nombre);
            p.setMarca(marca);
            p.setPrecio(precio);
            p.setCategoria(categoria);


            databaseReference.child("Producto").child(p.getId()).setValue(p);


        }catch(Exception e){


            Toast.makeText(this,"ERROR:" + e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }


}
