package com.example.firebase3.views.delito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.firebase3.R;
import com.example.firebase3.controller.GeneralController;
import com.example.firebase3.estructural.Antecedente;
import com.example.firebase3.estructural.Delito;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListDelito extends AppCompatActivity {

    private GeneralController generalController;
    FirebaseFirestore db;

    private EditText txtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_delito);

        generalController = GeneralController.getInstance();
        db = generalController.getDb();

        txtList = (EditText) findViewById(R.id.txtMultiLine);
    }

    public void listarDelitos(View view){
        final ArrayList<Delito> delitos = new ArrayList<>();


        db.collection(Delito.COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            StringBuffer sb = new StringBuffer();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Delito delito = document.toObject(Delito.class);
                                delitos.add(delito);
                                sb.append(delito.toString());
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