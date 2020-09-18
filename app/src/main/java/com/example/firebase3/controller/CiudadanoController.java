package com.example.firebase3.controller;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.firebase3.MainActivity;
import com.example.firebase3.estructural.Ciudadano;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Future;

public class CiudadanoController {

    private FirebaseFirestore db;

    public CiudadanoController(FirebaseFirestore db) {
        this.db = db;
    }

    public Ciudadano darCiudadano(String cedula)
    {
        final Ciudadano[] ciudadano = new Ciudadano[1];
        DocumentReference docRef = db.collection(Ciudadano.COLLECTION).document(cedula);
         docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ciudadano[0] = documentSnapshot.toObject(Ciudadano.class);

                //Aquí se estraen los latos y se muestran.
            }
        });
        return ciudadano[0];
    }

    public void agregarCiudadano(Ciudadano ciudadano)
    {

        //   Toast.makeText(MainActivity.this,"Ok!",Toast.LENGTH_LONG).show();
        System.out.println("Entro al boton");
        db.collection(Ciudadano.COLLECTION)
                .document(ciudadano.getCedula())
                .set(ciudadano).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //MENSAJE
          //          Toast.makeText(MainActivity.this,"Ciudadano adicionado!",Toast.LENGTH_LONG).show();
                    System.out.println("Success");
                }else{
                    //MENSAJE ERROR
          //          Toast.makeText(MainActivity.this,"Ciudadano NO adicionado!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Es exactamente el mismo de agregar
    public void actualizarCiudadano(Ciudadano ciudadano)
    {

        //   Toast.makeText(MainActivity.this,"Ok!",Toast.LENGTH_LONG).show();
        System.out.println("Entro al boton");
        db.collection(Ciudadano.COLLECTION)
                .document(ciudadano.getCedula())
                .set(ciudadano).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //MENSAJE
                    //          Toast.makeText(MainActivity.this,"Ciudadano adicionado!",Toast.LENGTH_LONG).show();
                    System.out.println("Success");
                }else{
                    //MENSAJE ERROR
                    //          Toast.makeText(MainActivity.this,"Ciudadano NO adicionado!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void eliminarCiudadano(String cedula){
        DocumentReference docRef = db.collection(Ciudadano.COLLECTION).document(cedula);
        docRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //Mensaje si se envió la peticion sin errores
                  //  Toast.makeText(MainActivity.this,"Ciudadano Eliminado!",Toast.LENGTH_LONG).show();
                    System.out.println("Success");
                }else{
                    //Error al hacer la peticion
                  //  Toast.makeText(MainActivity.this,"Ciudadano NO fue eliminado!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void listarCiudadanos(){
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
                            //Poner aquí la actualizacion de la lista

                        //    Toast.makeText(GetCiudadano.this,"Cantidad de ciudadanos: "+ ciudadanos.size(),Toast.LENGTH_LONG).show();
                        } else {
                            //Error al hacer la peticion
                        //    Toast.makeText(GetCiudadano.this,"Error al listar ciudadanos!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
