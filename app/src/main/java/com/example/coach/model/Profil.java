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

    /**
     * Constructeur de l'objet Profil
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.img = calculImg();
        this.indice = calculIndice();
    }

    /**
     * Calcul de l'indice de masse graisseuse (IMG)
     * @return
     */
    private double calculImg() {
        double tailleMetres = taille / 100.0;
        return (1.2 * poids / (tailleMetres * tailleMetres))
                + (0.23 * age)
                - (10.83 * sexe)
                - 5.4;
    }

    /**
     * Calcule l'indice (0 = maigre; 1 = normal; 2 = surpoids)
     * @return
     */
    private int calculIndice() {
        int min, max;
        if (sexe == 0) { min = MIN_FEMME; max = MAX_FEMME; } else { min = MIN_HOMME; max = MAX_HOMME; }
        if ( img > max ) { return 2; } else if ( img >= min ) { return 1; } else { return 0; }
    }

    /**
     * Renvoie l'indice de masse graisseuse.
     * @return
     */
    public double getImg() {
        return img;
    }

    /**
     * Renvoie le message correspondant à l'indice du profil
     * @return
     */
    public String getMessage() {
        return MESSAGE[indice];
    }

    /**
     * Renvoie l'image correspondant à l'indice du profil
     * @return
     */
    public String getImage() {
        return IMAGE[indice];
    }

    /**
     * Renvoie True si l'IMG est normal
     * @return
     */
    public boolean normal() {
        return indice == 1;
    }
}
