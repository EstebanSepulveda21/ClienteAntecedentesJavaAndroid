package com.example.firebase3.views.ciudadano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.firebase3.R;
import com.example.firebase3.controller.GeneralController;
import com.example.firebase3.estructural.Ciudadano;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListCiudadano extends AppCompatActivity {

    private GeneralController generalController;
    FirebaseFirestore db;

    private EditText txtList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ciudadano);

        generalController = GeneralController.getInstance();
        db = generalController.getDb();

        txtList = (EditText) findViewById(R.id.txtMultiLine);
    }

    public void listarCiudadanos(View view){
        final ArrayList<Ciudadano> ciudadanos = new ArrayList<>();


        db.collection(Ciudadano.COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            StringBuffer sb = new StringBuffer();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Ciudadano ciudadano = document.toObject(Ciudadano.class);
                                ciudadanos.add(ciudadano);
                                sb.append(ciudadano.toString());
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