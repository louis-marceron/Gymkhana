package fr.umontpellier.iut.gymkhana.model;

import fr.umontpellier.iut.gymkhana.model.pieces.Arete;
import fr.umontpellier.iut.gymkhana.model.pieces.Piece;
import fr.umontpellier.iut.gymkhana.model.pieces.Sommet;
import fr.umontpellier.iut.gymkhana.model.pieces.Vide;

/**
 * La classe {@code ImpressionPlateau} contient une unique méthode statique {@code toStringPlateau}
 * qui permet de représenter de façon esthétique la matrice sur la console.
 */
public class ImpressionPlateau {

    /**
     * Retourne une représentation la matrice d'un objet {@code Plateau}
     * en affichant les sommets par des points colorés et en indiquant
     * les valeurs des axes x et y.
     *
     * @param p le plateau qu'on veut représenter
     * @return le {@code String} de la matrice de {@code p}
     */
    public static String toStringPlateau(Plateau p) {
        StringBuilder str = new StringBuilder();
        Piece[][] tab = p.getMatrice();

        // Indices de l'axe horizontal en haut
        str.append("   ");
        for (int i = 0; i < tab.length; i++) {
            str.append(i).append("   ");
        }
        str.append("\n");


        for (int i = 0; i < tab.length; i++) {

            // Indices de l'axe vertical
            str.append("\u001B[0m").append(i);
            if (i < 10) // Plus petit espace après le "10" car il prend plus d'espace que les autres chiffres
                str.append("  ");
            else str.append(" ");

            // Remplace les valeurs de la matrice par des points colorés et par B/R pour les arêtes
            for (int j = 0; j < tab[i].length; j++) {
                if (i % 2 != 0 && j % 2 == 0) str.append("\u001B[31m"); // Met les points des sommets Rouges en rouge
                if (tab[i][j] == null || tab[i][j].getClass().equals(Vide.class)) {
                    str.append("    ");
                } else if (tab[i][j].getClass().equals(Sommet.class)) { //"SB".equals(tab[i][j]) || "SR".equals(tab[i][j])
                    str.append(".   ");
                } else if (tab[i][j].getClass().equals(Arete.class)) { //"AB".equals(tab[i][j])
                    if (((Arete)(tab[i][j])).getCouleur() == Couleur.Blanc) {
                        str.append("B   ");
                    } else {
                        str.append("R   ");
                    }
                }
            }
            str.append("\n");
        }


        // Indices de l'axe horizontal en bas
        str.append("   ");
        for (int i = 0; i < tab.length; i++) {
            str.append(i).append("   ");
        }
        str.append("\n");


        return str.toString();
    }
}
