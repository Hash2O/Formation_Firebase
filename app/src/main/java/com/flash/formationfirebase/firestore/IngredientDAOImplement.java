package com.flash.formationfirebase.firestore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.flash.formationfirebase.entities.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class IngredientDAOImplement {

    private FirebaseFirestore firebaseFirestore;
    private final String collectionPath;

    public IngredientDAOImplement(DAO dao) {
        this.firebaseFirestore = dao.getInstance();
        this.collectionPath = Ingredient.class.getSimpleName() + "s";
    }

    public void addIngredient(Ingredient ingredient) {

        //Instance de la BDD
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection(this.collectionPath)
                .add(ingredient)
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

    public void getListIngredient() {
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
                                //Ici, on obtient un objet Ingredient
                                Ingredient ingredient = documentSnapshot.toObject(Ingredient.class);
                                Log.v("Firebase Succes", ingredient.toString());
                            }
                        } else {
                            Log.v("Firebase erreur", String.valueOf(task.getException()));
                        }
                    }
                });
    }

    //ici, on passe par la méthode set() plutot qu'update() (cette dernière demande des infos directement)
    public void updateIngredient(Ingredient ingredient, String documentPath) {
        this.firebaseFirestore
                .collection(this.collectionPath)
                .document(documentPath)
                .set(ingredient)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("Firebase Succes", "Success update ingredient");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("Firebase erreur", "Error update ingredient");
                    }
                });
    }

    public void deleteIngredient(String documentPath) {
        this.firebaseFirestore
                .collection(this.collectionPath)
                .document(documentPath)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.v("Firebase Succes", "Success delete ingredient");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v("Firebase erreur", "Error delete ingredient");
                    }
                });
    }


}

