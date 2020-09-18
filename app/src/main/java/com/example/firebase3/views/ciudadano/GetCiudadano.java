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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GetCiudadano extends AppCompatActivity {

    private GeneralController generalController;
    FirebaseFirestore db;

    private EditText txtCedula;
    private EditText txtTipoDocumento;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ciudadano2);
        generalController = GeneralController.getInstance();
        db = generalController.getDb();

        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtTipoDocumento = (EditText) findViewById(R.id.txtTipoDocumento);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtFecha = (EditText) findViewById(R.id.txtFecha);

    }

    //Ejemplo dar uno
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
                    return;
                }
                txtNombre.setText(ciudadano.getNombre());
                txtFecha.setText(sdf.format(ciudadano.getFecha()));
                txtApellido.setText((ciudadano.getApellido()));
                txtTipoDocumento.setText("" + ciudadano.getTipoDocumento());

            }
        });
    }

    //Ejemplo dar TODOS
    public void actionButtonListarClick(View view)
    {
        /*
        final ArrayList<Ciudadano> ciudadanos = new ArrayList<>();
        db.collection(Ciudadano.COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Ciudadano ciudadano = document.toObject(Ciudadano.class);
                                ciudadanos.add(ciudadano);
                            }
                            Toast.makeText(GetCiudadano.this,"Cantidad de ciudadanos: "+ ciudadanos.size(),Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(GetCiudadano.this,"Error al listar ciudadanos!",Toast.LENGTH_LONG).show();
                        }
                    }
                });


         */
    }

}