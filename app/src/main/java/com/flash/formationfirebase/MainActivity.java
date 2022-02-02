package com.flash.formationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.flash.formationfirebase.R;
import com.flash.formationfirebase.entities.Recette;
import com.flash.formationfirebase.entities.Utilisateur;
import com.flash.formationfirebase.firestore.DAO;
import com.flash.formationfirebase.firestore.RecetteDAOImplement;
import com.flash.formationfirebase.firestore.UtilisateurDAOImplement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Sur les BDD NoSQL, il faut définir sa propre architecture
 * car il n'y a pas de relation implicte (pas de tables, de clés primaires)
 * Ce sont les attributs qui définissent les liens (comme
 * userId dans Recette par exemple, pour lier User et Recette)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



/**
 * Partie de l'appli reliée à la BD RealTimeDatabase de FireBase
 */
        /*
        //Instanciation de la base de données
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Element racine de la base de données
        DatabaseReference referenceDatabase = database.getReference("message");

        //Insertion d'un élément dans la base avec setValue
        //NB : si on ne change pas la reference du message, le suivant qui
        //aura la même ref écrasera le précédent
        referenceDatabase.setValue("Hello World, ici c'est Android !");

        //Avec push(), la bd comprend que le nouvel élément s'inscrit à la suite
        //du précédent portant la même ref
        referenceDatabase.push().setValue("Hello Firebase, depuis l'appi Formation Firebase");*/


/**
 * Partie de l'appli reliée à la base de données Firestore de Firebase
  */

    //Création d'un utilisateur
        Utilisateur user = new Utilisateur("Peter", "spiderman@mail.com");
        Utilisateur user2 = new Utilisateur("Carole", "marvel@mail.com");

        Recette recette = new Recette("Sushi", "Entrée japonaise", "NVFa345efn50BXR");

        /**
         * Suite du singleton pattern
         * Ici, on est sûr d'avoir une et une seule instanciation
         * à la fois
         */
        DAO dao = new DAO();

        //Instanciation
        UtilisateurDAOImplement utilisateurDAOImplement = dao.getUtilisateurDAOImplement();

        //Ajout d'un utilisateur
        utilisateurDAOImplement.addUtilisateur(user);

        //Affichage de la liste
        utilisateurDAOImplement.getListUtilisateur();

        //documentPath fictif, pour l'exemple
        String documentPath = "NVFa345efn50BXR";
        utilisateurDAOImplement.updateUtilisateur(user, documentPath);

        //suppression avec le même documentPath
        utilisateurDAOImplement.deleteUtilisateur(documentPath);


        //Instanciation recette
        RecetteDAOImplement recetteDAOImplement = dao.getRecetteDAOImplement();

        //Ajout recette
        recetteDAOImplement.addRecette(recette);

        //Affichage liste recette
        recetteDAOImplement.getListRecette();

    }
}