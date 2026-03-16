package com.example.coach.contract;

import android.content.Context;
import android.widget.Toast;

public interface ICalculView extends IAllView {
    /**
     * Fonction abstraite qui sera définie dans la vue. Permet d'afficher le résultat.
     * @param image
     * @param img
     * @param message
     * @param normal
     */
    void afficherResultat(String image, double img, String message, boolean normal);

    /**
     * Fonction abstraite qui sera redéfinie dans la vue. Permet de remplir les champs avec des données pré-existantes.
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    void remplirChamps(Integer poids, Integer taille, Integer age, Integer sexe);

    @Override
    default void afficherMessage(String message) {
        Toast.makeText((Context) this, message, Toast.LENGTH_SHORT).show();
    }
}
