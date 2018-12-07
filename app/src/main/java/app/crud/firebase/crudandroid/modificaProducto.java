package app.crud.firebase.crudandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.crud.firebase.crudandroid.Model.Producto;

public class modificaProducto extends AppCompatActivity {

    EditText nombrep,marcap,preciop,categoriap;
    Button btn_modificar,btn_eliminar;

    String nombre,marca,precio,categoria,id;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_producto);

        inicializarFirebase();

        Bundle b = getIntent().getExtras();
        if(b!=null){
            id = b.getString("id");
            nombre = b.getString("nombre");
            marca = b.getString("marca");
            precio = b.getString("precio");
            categoria = b.getString("categoria");
        }

        nombrep = findViewById(R.id.txt_nombrep);
        marcap = findViewById(R.id.txt_marca);
        preciop = findViewById(R.id.txt_precio);
        categoriap = findViewById(R.id.txt_categoria);

        nombrep.setText(nombre);
        marcap.setText(marca);
        preciop.setText(precio);
        categoriap.setText(categoria);

        btn_modificar = (Button) findViewById(R.id.btnModificarProducto);
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



        Producto p = new Producto();
        p.setId(id);
        p.setNombre(nombrep.getText().toString().trim());
        p.setMarca(marcap.getText().toString().trim());
        p.setPrecio(preciop.getText().toString().trim());
        p.setCategoria(categoriap.getText().toString().trim());
        databaseReference.child("Producto").child(p.getId()).setValue(p);
        Toast.makeText(this,"Modificado", Toast.LENGTH_SHORT).show();

    }

    private void Eliminar(){
        Producto p = new Producto();
        p.setId(id);
        databaseReference.child("Producto").child(p.getId()).removeValue();
        Toast.makeText(this,"Eliminado", Toast.LENGTH_SHORT).show();
    }

}
