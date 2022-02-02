package com.flash.formationfirebase.firestore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.flash.formationfirebase.entities.Utilisateur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UtilisateurDAOImplement {

    private FirebaseFirestore firebaseFirestore;
    private final String collectionPath;

    public UtilisateurDAOImplement(DAO dao) {
        this.firebaseFirestore = dao.getInstance();
        this.collectionPath = Utilisateur.class.getSimpleName() + "s";
    }

    public void addUtilisateur(Utilisateur utilisateur) {

        //Instance de la BDD
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection(this.collectionPath)
                .add(utilisateur)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.v("Firebase succès", documentReference.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("Firebase erreur", e.getMessage());
                    }
                });

    }

    public void getListUtilisateur() {
        this.firebaseFirestore
                .collection(this.collectionPath)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                //ici, on obtient la liste des données
                                Log.v("Firebase Succes", documentSnapshot.getData().toString());
                                //Ici, on obtient un objet Utilisateur
                                Utilisateur user = documentSnapshot.toObject(Utilisateur.class);
                                Log.v("Firebase Succes", user.toString());
                            }
                        } else {
                            Log.v("Firebase erreur", String.valueOf(task.getException()));
                        }
                    }
                });
    }

    //Implémenter la méthode pour trouver le document qui contient les infos genre email

    public void findUtilisateurByEmail(String email) {

    }

    //ici, on passe par la méthode set() plutot qu'update() (cette dernière demande des infos directement)
    public void updateUtilisateur(Utilisateur utilisateur, String documentPath) {
        this.firebaseFirestore
                .collection(this.collectionPath)
                .document(documentPath)
                .set(utilisateur)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("Firebase Succes", "Success update user");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("Firebase erreur", "Error update user");
                    }
                });
    }

    public void deleteUtilisateur(String documentPath){
        this.firebaseFirestore
                .collection(this.collectionPath)
                .document(documentPath)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("Firebase Succes", "Success delete user");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("Firebase erreur", "Error delete user");
                    }
                });
    }

}
