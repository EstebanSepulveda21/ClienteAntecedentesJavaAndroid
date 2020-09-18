package com.example.firebase3.views.antecedente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase3.MainActivity;
import com.example.firebase3.R;
import com.example.firebase3.controller.GeneralController;
import com.example.firebase3.estructural.Antecedente;
import com.example.firebase3.estructural.Ciudadano;
import com.example.firebase3.views.ciudadano.AddCiudadano;
import com.example.firebase3.views.ciudadano.ListCiudadano;
import com.example.firebase3.views.delito.ListDelito;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAntecedente extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_antecedente);

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

    public void btnAgregarClick(View view)
    {
        try {
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(txtFechaDelito.getText()));

            Antecedente antecedente = new Antecedente();
            antecedente.setId(Integer.parseInt(String.valueOf(txtId.getText())));
            antecedente.setCiudadanoDi(String.valueOf(txtCiudadanoDi.getText()));
            antecedente.setDelitoCodigo(Integer.parseInt(String.valueOf(txtCodigoDelito.getText())));
            antecedente.setCiudad(String.valueOf(txtCiudad.getText()));
            antecedente.setFechaDelito(fecha);
            antecedente.setSentencia(Integer.parseInt(String.valueOf(txtSentencia.getText())));
            antecedente.setEstado(String.valueOf(txtEstado.getText()));
            //   Toast.makeText(MainActivity.this,"Ok!",Toast.LENGTH_LONG).show();
            System.out.println("Entro al boton");
            db.collection(Antecedente.COLLECTION)
                    .document(String.valueOf(antecedente.getId()))
                    .set(antecedente).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddAntecedente.this,"Antecedente adicionado!",Toast.LENGTH_LONG).show();
                        System.out.println("Success");
                        txtId.setText("");
                        txtCiudadanoDi.setText("");
                        txtCodigoDelito.setText("");
                        txtCiudad.setText("");
                        txtFechaDelito.setText("");
                        txtSentencia.setText("");
                        txtEstado.setText("");
                    }else{
                        Toast.makeText(AddAntecedente.this,"Error al adicionar Antecedente!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void irAlActivityListCiudadano(View view)
    {
        Intent intent = new Intent(AddAntecedente.this, ListCiudadano.class);
        startActivity(intent);
    }

    public void irAlActivityListDelito(View view)
    {
        Intent intent = new Intent(AddAntecedente.this, ListDelito.class);
        startActivity(intent);
    }

}