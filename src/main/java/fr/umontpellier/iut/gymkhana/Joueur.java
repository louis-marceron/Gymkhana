package fr.umontpellier.iut.gymkhana;

public interface Joueur {
    //doit retourner vrai si le coup qui viens d'être joué est gagnant
    public boolean jouer(Plateau plateau, Couleur couleur);
}
