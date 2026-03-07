package com.example.coach.contract;

public interface ICalculView {
    /**
     * Fonction abstraite qui sera définie dans la vue.
     * @param image
     * @param img
     * @param message
     * @param normal
     */
    void afficherResultat(String image, double img, String message, boolean normal);
}
