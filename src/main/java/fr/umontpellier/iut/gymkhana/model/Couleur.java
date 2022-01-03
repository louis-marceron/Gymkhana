package fr.umontpellier.iut.gymkhana.model;

public enum Couleur {
    Rouge, Blanc;

    public String nomArete() {
        return this == Rouge ? "AR" : "AB";
    }

    public String nomSommet() {
        return this == Rouge ? "SR" : "SB";
    }

    public String nomCouleur() {
        return this == Rouge ? "Rouge" : "Blanc";
    }
}

//TODO enlever méthodes inutiles
