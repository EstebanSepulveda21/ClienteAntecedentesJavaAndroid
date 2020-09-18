package com.example.firebase3.controller;

import com.google.firebase.firestore.FirebaseFirestore;

public class TipoDocumentoController {

    private FirebaseFirestore db;

    public TipoDocumentoController(FirebaseFirestore db) {
        this.db = db;
    }

}
