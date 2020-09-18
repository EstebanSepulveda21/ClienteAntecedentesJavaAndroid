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

import java.text.SimpleDateFormat;

public class DeleteCiudadano extends AppCompatActivity {

    private GeneralController generalController;
    FirebaseFirestore db;

    private EditText txtCedula;
    private EditText txtTipoDocumento;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtFecha;

    private Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ciudadano);

        generalController = GeneralController.getInstance();
        db = generalController.getDb();

        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtTipoDocumento = (EditText) findViewById(R.id.txtTipoDocumento);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        btnEliminar = (Button) findViewById(R.id.buttonActualizar);
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
                    btnEliminar.setEnabled(false);
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
                btnEliminar.setEnabled(true);
            }
        });
    }

    public void btnEliminarClick(View view)
    {
        DocumentReference docRef = db.collection(Ciudadano.COLLECTION).document(String.valueOf(txtCedula.getText()));
        docRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DeleteCiudadano.this,"Ciudadano Eliminado!",Toast.LENGTH_LONG).show();
                    System.out.println("Success");

                    txtNombre.setText("");
                    txtFecha.setText("");
                    txtApellido.setText("");
                    txtTipoDocumento.setText("");

                    txtNombre.setEnabled(false);
                    txtFecha.setEnabled(false);
                    txtApellido.setEnabled(false);
                    txtTipoDocumento.setEnabled(false);
                    btnEliminar.setEnabled(false);
                }else{
                    Toast.makeText(DeleteCiudadano.this,"Error al eliminar!",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}