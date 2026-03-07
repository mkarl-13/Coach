package com.example.coach.model;

public class Profil {
    private static final int MIN_FEMME = 25;
    private static final int MAX_FEMME = 30;
    private static final int MIN_HOMME = 15;
    private static final int MAX_HOMME = 20;

    private static final String[] MESSAGE = {"trop faible", "normal", "trop élevé"};
    private static final String[] IMAGE = {"maigre", "normal", "graisse"};

    private Integer poids, taille, age, sexe;

    private double img;
    private int indice;

    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.img = calculImg();
        this.indice = calculIndice();
    }

    private double calculImg() {
        double tailleMetres = taille / 100.0;
        return (1.2 * poids / (tailleMetres * tailleMetres))
                + (0.23 * age)
                - (10.83 * sexe)
                - 5.4;
    }

    private int calculIndice() {
        int min, max;
        if (sexe == 0) { min = MIN_FEMME; max = MAX_FEMME; } else { min = MIN_HOMME; max = MAX_HOMME; }
        if ( img > max ) { return 2; } else if ( img >= min ) { return 1; } else { return 0; }
    }

    public double getImg() {
        return img;
    }

    public String getMessage() {
        return MESSAGE[indice];
    }

    public String getImage() {
        return IMAGE[indice];
    }

    public boolean normal() {
        return indice == 1;
    }
}
