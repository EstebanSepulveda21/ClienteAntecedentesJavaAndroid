package com.example.firebase3.controller;

import com.example.firebase3.estructural.Ciudadano;
import com.google.firebase.firestore.FirebaseFirestore;

public class GeneralController {

    private static GeneralController generalController;

    private FirebaseFirestore db;

    private CiudadanoController ciudadanoController;
    private AntecedenteController antecedenteController;
    private DelitoController delitoController;
    private TipoDocumentoController tipoDocumentoController;

    private GeneralController() {
        db = FirebaseFirestore.getInstance();
        ciudadanoController = new CiudadanoController(db);
        antecedenteController = new AntecedenteController(db);
        delitoController = new DelitoController(db);
        tipoDocumentoController = new TipoDocumentoController(db);
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public static GeneralController getInstance()
    {
        if(generalController==null)
            generalController = new GeneralController();
        return generalController;
    }

    public Ciudadano darCiudadano(String cedula)
    {
        return ciudadanoController.darCiudadano(cedula);
    }

}
