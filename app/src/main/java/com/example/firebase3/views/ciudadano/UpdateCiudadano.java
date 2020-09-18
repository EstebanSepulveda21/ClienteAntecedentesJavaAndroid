package com.example.firebase3.views.ciudadano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase3.R;
import com.example.firebase3.controller.GeneralController;
import com.example.firebase3.estructural.Ciudadano;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateCiudadano extends AppCompatActivity {

    private GeneralController generalController;
    FirebaseFirestore db;

    private EditText txtCedula;
    private EditText txtTipoDocumento;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtFecha;

    private Button btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ciudadano);

        generalController = GeneralController.getInstance();
        db = generalController.getDb();

        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtTipoDocumento = (EditText) findViewById(R.id.txtTipoDocumento);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        btnActualizar = (Button) findViewById(R.id.buttonActualizar);
    }

    public void buscarCiudadanoClick(View view)
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String cedula = txtCedula.getText().toString();
        DocumentReference docRef = db.collection(Ciudadano.COLLECTION).document(cedula);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Ciudadano ciudadano = documentSnapshot.toObject(Ciudadano.class);
                if(ciudadano==null) {
                    txtNombre.setText("");
                    txtFecha.setText("");
                    txtApellido.setText("");
                    txtTipoDocumento.setText("");

                    txtNombre.setEnabled(false);
                    txtFecha.setEnabled(false);
                    txtApellido.setEnabled(false);
                    txtTipoDocumento.setEnabled(false);
                    btnActualizar.setEnabled(false);
                    return;
                }
                txtNombre.setText(ciudadano.getNombre());
                txtFecha.setText(sdf.format(ciudadano.getFecha()));
                txtApellido.setText((ciudadano.getApellido()));
                txtTipoDocumento.setText("" + ciudadano.getTipoDocumento());
                txtNombre.setEnabled(true);
                txtFecha.setEnabled(true);
                txtApellido.setEnabled(true);
                txtTipoDocumento.setEnabled(true);
                btnActualizar.setEnabled(true);
            }
        });
    }

    public void btnActualizarClick(View view)
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
                        Toast.makeText(UpdateCiudadano.this,"Ciudadano Actualizado!",Toast.LENGTH_LONG).show();
                        System.out.println("Success");

                        txtNombre.setText("");
                        txtFecha.setText("");
                        txtApellido.setText("");
                        txtTipoDocumento.setText("");

                        txtNombre.setEnabled(false);
                        txtFecha.setEnabled(false);
                        txtApellido.setEnabled(false);
                        txtTipoDocumento.setEnabled(false);
                        btnActualizar.setEnabled(false);
                    }else{
                        Toast.makeText(UpdateCiudadano.this,"Error al actualizar!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}