package com.example.firebase3.views.antecedente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.firebase3.R;
import com.example.firebase3.controller.GeneralController;
import com.example.firebase3.estructural.Antecedente;
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

public class GetAntecedente extends AppCompatActivity {

    GeneralController generalController;
    FirebaseFirestore db;

    private EditText txtId;
    private EditText txtCiudadanoDi;
    private EditText txtCodigoDelito;
    private EditText txtCiudad;
    private EditText txtFechaDelito;
    private EditText txtSentencia;
    private EditText txtEstado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_antecedente);

        generalController = GeneralController.getInstance();
        db = generalController.getDb();

        txtId = (EditText) findViewById(R.id.txtID);
        txtCiudadanoDi = (EditText) findViewById(R.id.txtCiudadanoDi);
        txtCodigoDelito = (EditText) findViewById(R.id.txtCodigoDelito);
        txtCiudad = (EditText) findViewById(R.id.txtCiudad);
        txtFechaDelito = (EditText) findViewById(R.id.txtFecha);
        txtSentencia = (EditText) findViewById(R.id.txtSentencia);
        txtEstado = (EditText) findViewById(R.id.txtEstado);

    }

    public void buscarAntecedenteClick(View view)
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String id = txtId.getText().toString();
        DocumentReference docRef = db.collection(Antecedente.COLLECTION).document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Antecedente antecedente = documentSnapshot.toObject(Antecedente.class);
                if(antecedente==null) {
                    txtId.setText("");
                    txtCiudadanoDi.setText("");
                    txtCodigoDelito.setText("");
                    txtCiudad.setText("");
                    txtFechaDelito.setText("");
                    txtSentencia.setText("");
                    txtEstado.setText("");
                    return;
                }
                txtId.setText("" + antecedente.getId());
                txtCiudadanoDi.setText(antecedente.getCiudadanoDi());
                txtCodigoDelito.setText("" + antecedente.getDelitoCodigo());
                txtCiudad.setText(antecedente.getCiudad());
                txtFechaDelito.setText(sdf.format(antecedente.getFechaDelito()));
                txtSentencia.setText("" + antecedente.getSentencia());
                txtEstado.setText(antecedente.getEstado());
            }
        });
    }

}