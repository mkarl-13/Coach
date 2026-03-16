package com.example.coach.contract;

import android.content.Context;
import android.widget.Toast;

import com.example.coach.model.Profil;

import java.util.List;

public interface IHistoView extends IAllView {
    void afficherListe(List profils);
    void transfertProfil(Profil profil);

    @Override
    default void afficherMessage(String message) {
        Toast.makeText((Context) this, message, Toast.LENGTH_SHORT).show();
    }
}
