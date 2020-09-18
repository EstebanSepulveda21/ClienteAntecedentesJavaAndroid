package com.example.firebase3.views.ciudadano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase3.MainActivity;
import com.example.firebase3.R;
import com.example.firebase3.controller.GeneralController;
import com.example.firebase3.estructural.Ciudadano;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCiudadano extends AppCompatActivity {

    GeneralController generalController;
    FirebaseFirestore db;

    private EditText txtCedula;
    private EditText txtTipoDocumento;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ciudadano);
        generalController = GeneralController.getInstance();
        db = generalController.getDb();

        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtTipoDocumento = (EditText) findViewById(R.id.txtTipoDocumento);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
    }

    public void btnAgregarClick(View view)
    {
        try {
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(txtFecha.getText()));

            Ciudadano ciudadano = new Ciudadano(txtCedula.getText().toString(), fecha, Integer.parseInt(String.valueOf(txtTipoDocumento.getText())), String.valueOf(txtNombre.getText()), String.valueOf(txtApellido.getText()));
            //   Toast.makeText(MainActivity.this,"Ok!",Toast.LENGTH_LONG).show();
            System.out.println("Entro al boton");
            db.collection(Ciudadano.COLLECTION)
                    .document(ciudadano.getCedula())
                    .set(ciudadano).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddCiudadano.this,"Ciudadano adicionado!",Toast.LENGTH_LONG).show();
                        System.out.println("Success");
                        txtCedula.setText("");
                        txtNombre.setText("");
                        txtFecha.setText("");
                        txtApellido.setText("");
                        txtTipoDocumento.setText("");
                    }else{
                        Toast.makeText(AddCiudadano.this,"Ciudadano NO adicionado!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}