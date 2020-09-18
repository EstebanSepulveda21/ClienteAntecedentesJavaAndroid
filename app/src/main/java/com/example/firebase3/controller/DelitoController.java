package com.example.firebase3.controller;

import com.google.firebase.firestore.FirebaseFirestore;

public class DelitoController {

    private FirebaseFirestore db;

    public DelitoController(FirebaseFirestore db) {
        this.db = db;
    }

}
