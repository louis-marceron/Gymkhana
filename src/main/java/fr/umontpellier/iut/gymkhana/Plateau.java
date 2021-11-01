package fr.umontpellier.iut.gymkhana;

import java.util.ArrayList;

public class Plateau {
    private Joueur joueurRouge;
    private Joueur joueurBlanc;
    private final int taille;
    private String[][] matrice;

    /*
    L'attribut "matrice" correpond à une matrice comprenant les sommets rouges et blancs, et leurs arrêtes respectives
    NA = case non assignée (correspond aux 4 coins du plateau)
    null = arrête ne comprenant pas d'arrête
    SR = sommet rouge
    SB = sommet blanc
    AR = arrête rouge
    AB = arrête blanche
    TODO remplacer null par V (vide)
    */

    public Plateau(int taille) {
        this.taille = taille;

        int dimension = taille * 2 + 1;
        matrice = new String[dimension][dimension];

        // On met "NA" dans les quatres coins de la matrice
        matrice[0][0] = "NA";
        matrice[matrice.length - 1][0] = "NA";
        matrice[0][matrice.length - 1] = "NA";
        matrice[matrice.length - 1][matrice.length - 1] = "NA";

        // On place tous les sommets
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 1)
                        matrice[i][j] = "SB";
                }
                if (i % 2 == 1) {
                    if (j % 2 == 0)
                        matrice[i][j] = "SR";
                }
            }
        }
    }

    public int getTaille() {
        return taille;
    }

    public String[][] getMatrice() {
        return matrice;
    }

    @Override
    public String toString() {
        return ImpressionPlateau.impression(this);
    }

    public boolean ajouterArrete(int[] a1, int[] a2, Couleur c) {
        // TODO try/catch + tests unitaires
        // les indices ne sont pas trop grands
        if(a1[0] > taille * 2 || a1[1] > taille * 2 || a2[0] > taille * 2 || a2[1] > taille * 2)
            return false;

        // les deux cases non nulles
        if (matrice[a1[0]][a1[1]] == null || matrice[a2[0]][a2[1]] == null)
            return false;

        // les deux cases sont toutes les deux SR ou toutes les deux SB
        if (!matrice[a1[0]][a1[1]].equals(c.nomSommet()) || !matrice[a2[0]][a2[1]].equals(c.nomSommet()))
            return false;

        // il y a une case d'écart entre les deux cases
        if (Math.abs(a1[0] - a2[0]) + Math.abs(a1[1] - a2[1]) != 2)
            return false;

        // l'arrête est uniquement sur une case null
        if (matrice[(a1[0] + a2[0])/2][(a1[1] + a2[1])/2] != null)
            return false;

        matrice[(a1[0] + a2[0])/2][(a1[1] + a2[1])/2] = c.nomArrete();
        return true;
    }

    public ArrayList<int[]> voisins(int[] s, Couleur c) {
        // TODO tests unitaires
        ArrayList<int[]> voisins = new ArrayList<>();

        // S'il y a une arrête à gauche du sommet s (correspondant à sa couleur),
        // il y a un voisin à deux cases à gauche de s.
        if (s[1] > 0 && matrice[s[0]][s[1]-1] != null && matrice[s[0]][s[1]-1].equals(c.nomArrete()))
            voisins.add(new int[]{s[0], s[1]-2});

        // Voisin deux cases à droite de s
        if (s[1] < 2 * taille && matrice[s[0]][s[1]+1] != null && matrice[s[0]][s[1]+1].equals(c.nomArrete()))
            voisins.add(new int[]{s[0], s[1]+2});

        // Voisin deux cases au-dessus de s
        if (s[0] > 0 && matrice[s[0]-1][s[1]] != null && matrice[s[0]-1][s[1]].equals(c.nomArrete()))
            voisins.add(new int[]{s[0]-2, s[1]});

        // Voisin deux cases en dessous de s
        if (s[1] < 2 * taille && matrice[s[0]+1][s[1]] != null && matrice[s[0]+1][s[1]].equals(c.nomArrete()))
            voisins.add(new int[]{s[0]+2, s[1]});

        return voisins;
    }
}
