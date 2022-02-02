package com.flash.formationfirebase.firestore;

import com.google.firebase.firestore.FirebaseFirestore;

public class DAO {

    FirebaseFirestore firebaseFirestore;

    /**
     * Constructeur sans params
     * Ici, approche singleton pattern
     */
    public DAO() {
        if(firebaseFirestore == null){
            this.firebaseFirestore = FirebaseFirestore.getInstance();
        }
    }

    public FirebaseFirestore getInstance() {
        return firebaseFirestore;
    }

    public UtilisateurDAOImplement getUtilisateurDAOImplement() {
        return new UtilisateurDAOImplement(this);
    }

    public RecetteDAOImplement getRecetteDAOImplement() {
        return new RecetteDAOImplement(this);
    }


}
