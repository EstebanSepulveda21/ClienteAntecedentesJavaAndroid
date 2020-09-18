package com.example.firebase3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebase3.estructural.Ciudadano;
import com.example.firebase3.views.antecedente.AddAntecedente;
import com.example.firebase3.views.antecedente.DeleteAntecedente;
import com.example.firebase3.views.antecedente.GetAntecedente;
import com.example.firebase3.views.antecedente.ListAntecedente;
import com.example.firebase3.views.antecedente.UpdateAntecedente;
import com.example.firebase3.views.ciudadano.AddCiudadano;
import com.example.firebase3.views.ciudadano.DeleteCiudadano;
import com.example.firebase3.views.ciudadano.GetCiudadano;
import com.example.firebase3.views.ciudadano.ListCiudadano;
import com.example.firebase3.views.ciudadano.UpdateCiudadano;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
    }

    //Ejemplo añadir - actualizar
    public void actionButtonClick(View view)
    {

        Ciudadano ciudadano = new Ciudadano("753456", new Date(), 1, "Wapo", "Apellido");
        //   Toast.makeText(MainActivity.this,"Ok!",Toast.LENGTH_LONG).show();
        System.out.println("Entro al boton");
        db.collection(Ciudadano.COLLECTION)
                .document(ciudadano.getCedula())
                .set(ciudadano).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Ciudadano adicionado!",Toast.LENGTH_LONG).show();
                    System.out.println("Success");
                }else{
                    Toast.makeText(MainActivity.this,"Ciudadano NO adicionado!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Ejemplo eliminar
    public void actionButton2Click(View view)
    {
        DocumentReference docRef = db.collection(Ciudadano.COLLECTION).document("prueba1");
        docRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Ciudadano Eliminado!",Toast.LENGTH_LONG).show();
                    System.out.println("Success");
                }else{
                    Toast.makeText(MainActivity.this,"Ciudadano NO fue eliminado!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void irAlActivityGetCiudadano(View view)
    {
        Intent intent = new Intent(MainActivity.this, GetCiudadano.class);
        startActivity(intent);
    }
    public void irAlActivityAddCiudadano(View view)
    {
        Intent intent = new Intent(MainActivity.this, AddCiudadano.class);
        startActivity(intent);
    }
    public void irAlActivityDeleteCiudadano(View view)
    {
        Intent intent = new Intent(MainActivity.this, DeleteCiudadano.class);
        startActivity(intent);
    }
    public void irAlActivityListCiudadano(View view)
    {
        Intent intent = new Intent(MainActivity.this, ListCiudadano.class);
        startActivity(intent);
    }
    public void irAlActivityUpdateCiudadano(View view)
    {
        Intent intent = new Intent(MainActivity.this, UpdateCiudadano.class);
        startActivity(intent);
    }
    public void irAlActivityGetAntecedente(View view)
    {
        Intent intent = new Intent(MainActivity.this, GetAntecedente.class);
        startActivity(intent);
    }
    public void irAlActivityAddAntecedente(View view)
    {
        Intent intent = new Intent(MainActivity.this, AddAntecedente.class);
        startActivity(intent);
    }
    public void irAlActivityDeleteAntecedente(View view)
    {
        Intent intent = new Intent(MainActivity.this, DeleteAntecedente.class);
        startActivity(intent);
    }
    public void irAlActivityListAntecedente(View view)
    {
        Intent intent = new Intent(MainActivity.this, ListAntecedente.class);
        startActivity(intent);
    }
    public void irAlActivityUpdateAntecedente(View view)
    {
        Intent intent = new Intent(MainActivity.this, UpdateAntecedente.class);
        startActivity(intent);
    }
    public void dialogCreadores(View view)
    {
        CreatorsDialog creatorsDialog = new CreatorsDialog();
        creatorsDialog.show(getSupportFragmentManager(), "Creadores");
    }

}