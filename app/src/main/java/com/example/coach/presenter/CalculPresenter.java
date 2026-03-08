package com.example.coach.presenter;

import android.content.Context;

import com.example.coach.contract.ICalculView;
import com.example.coach.data.ProfilDAO;
import com.example.coach.model.Profil;

import java.util.Date;

public class CalculPresenter {
    private ICalculView vue;
    private ProfilDAO profilDAO;

    /**
     * Crée le lien entre le presenter et la view.
     * @param vue
     */
    public CalculPresenter(ICalculView vue, Context context) {
        this.vue = vue;
        this.profilDAO = new ProfilDAO(context);
    }

    /**
     * Crée un objet Profil, puis appelle une fonction de la vue pour afficher le résultat.
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        Profil profil = new Profil(poids, taille, age, sexe, new Date());
        vue.afficherResultat(profil.getImage(), profil.getImg(), profil.getMessage(), profil.normal());
        profilDAO.insertProfil(profil);
    }

    public void chargerProfil() {
        Profil profil = profilDAO.getLastProfil();
        if (profil != null) {
            vue.remplirChamps(profil.getPoids(), profil.getTaille(), profil.getAge(), profil.getSexe());
        }
    }
}
