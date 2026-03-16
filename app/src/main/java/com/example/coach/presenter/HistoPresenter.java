package com.example.coach.presenter;

import com.example.coach.api.CoachApi;
import com.example.coach.api.HelperApi;
import com.example.coach.api.ICallbackApi;
import com.example.coach.contract.IHistoView;
import com.example.coach.model.Profil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HistoPresenter {
    private IHistoView vue;

    public HistoPresenter(IHistoView vue) {
        this.vue = vue;
    }
    public void chargerProfils() {
        HelperApi.call(HelperApi.getApi().getProfils(), new ICallbackApi<List<Profil>>() {
            @Override
            public void onSuccess(List<Profil> profils) {
                if (profils != null && !profils.isEmpty()) {
                    Collections.sort(
                            profils,
                            (p1, p2) -> p2.getDateMesure().compareTo(p1.getDateMesure()));
                    vue.afficherListe(profils);
                } else {
                    vue.afficherMessage("échec chargement du profil");
                }
            }

            @Override
            public void onError() {
                vue.afficherMessage("échec enregistrement du profil");
            }
        });
    }

    public void supprProfil(Profil profil, ICallbackApi<Void> callback) {
        String profilJson = CoachApi.getGson().toJson(profil);
        HelperApi.call(HelperApi.getApi().supprProfil(profilJson), new ICallbackApi<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                if (result == 1) {
                    callback.onSuccess(null);
                    vue.afficherMessage("profil supprimé");
                } else {
                    vue.afficherMessage("échec suppression du profil");
                }
            }

            @Override
            public void onError() {
                vue.afficherMessage("échec suppression du profil");
            }
        });
    }

    public void transfertProfil(Profil profil) {
        vue.transfertProfil(profil);
    }
}
