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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListAntecedente extends AppCompatActivity {

    private GeneralController generalController;
    FirebaseFirestore db;

    private EditText txtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_antecedente);

        generalController = GeneralController.getInstance();
        db = generalController.getDb();

        txtList = (EditText) findViewById(R.id.txtMultiLine);
    }

    public void listarAntecedentes(View view){
        final ArrayList<Antecedente> antecedentes = new ArrayList<>();


        db.collection(Antecedente.COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            StringBuffer sb = new StringBuffer();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Antecedente antecedente = document.toObject(Antecedente.class);
                                antecedentes.add(antecedente);
                                sb.append(antecedente.toString());
                                sb.append("\n\n");
                            }
                            //Poner aquí la actualizacion de la lista
                            txtList.setText(sb.toString());
                            //    Toast.makeText(GetCiudadano.this,"Cantidad de ciudadanos: "+ ciudadanos.size(),Toast.LENGTH_LONG).show();
                        } else {
                            //Error al hacer la peticion
                            //    Toast.makeText(GetCiudadano.this,"Error al listar ciudadanos!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}