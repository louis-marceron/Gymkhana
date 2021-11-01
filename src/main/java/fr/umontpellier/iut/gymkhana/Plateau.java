package fr.umontpellier.iut.gymkhana;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * La classe {@code Plateau} permet de créer instances d'un plateau du Gymkhana, modélisé
 * à travers une matrice d'incidence de type {@code String[][]}.
 * Cette matrice comprend la position de chaque sommet et de chaque arrête placée, représentés par
 * une chaine de caractères.
 * <p><b>Légende</b>
 * <blockquote>
 * NA = case non assignée (correspond aux 4 coins du plateau)<br>
 * V = case vide<br>
 * SR = sommet rouge<br>
 * SB = sommet blanc<br>
 * AR = arrête rouge<br>
 * AB = arrête blanche<br>
 * </blockquote>
 * <p>La classe {@code Plateau} comprend les méthodes pouvant placer des arrêtes et vérifier si un
 * {@code Joueur} est gagnant. Il y aussi des méthodes spécifiques aux Graphes, pouvant par exemple
 * retourner les voisins d'un sommet ou sa classe de connexité.
 */
public class Plateau {
    private final int taille;
    private Joueur joueurRouge;
    private Joueur joueurBlanc;
    private String[][] matrice;

    /**
     * Initialise un nouvel objet {@code Plateau} en construisant une matrice comprenant les cases
     * non assignées "NA", les cases vides "V" et les sommets rouges et blancs "SR" / "SB".
     *
     * @param taille
     *        La longueur des graphes Rouge et Blanc. Leur largeur est de {@code taille - 1}
     */
    public Plateau(int taille) {
        this.taille = taille;

        int dimension = taille * 2 + 1;
        matrice = new String[dimension][dimension];

        // On remplit la matrice de "V"
        for (String[] strings : matrice) Arrays.fill(strings, "V");

        matrice[0][0] = "NA";
        matrice[matrice.length - 1][0] = "NA";
        matrice[0][matrice.length - 1] = "NA";
        matrice[matrice.length - 1][matrice.length - 1] = "NA";

        // On place les sommets rouges et blancs
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

    public boolean ajouterArrete(int[] a1, int[] a2, Couleur c) {
        // TODO try/catch + tests unitaires
        // les indices ne sont pas trop grands
        if (a1[0] > taille * 2
                || a1[1] > taille * 2
                || a2[0] > taille * 2
                || a2[1] > taille * 2)
            return false;

        // les deux cases sont toutes les deux SR ou toutes les deux SB
        if (!matrice[a1[0]][a1[1]].equals(c.nomSommet())
                || !matrice[a2[0]][a2[1]].equals(c.nomSommet()))
            return false;

        // il y a une case d'écart entre les deux cases
        if (Math.abs(a1[0] - a2[0]) + Math.abs(a1[1] - a2[1]) != 2)
            return false;

        // l'arrête est uniquement sur une case vide
        if (!matrice[(a1[0] + a2[0]) / 2][(a1[1] + a2[1]) / 2].equals("V"))
            return false;

        matrice[(a1[0] + a2[0]) / 2][(a1[1] + a2[1]) / 2] = c.nomArrete();
        return true;
    }

    public ArrayList<int[]> getVoisinsSommet(int[] s, Couleur c) {
        // TODO tests unitaires
        ArrayList<int[]> voisins = new ArrayList<>();

        // S'il y a une arrête à gauche du sommet s (correspondant à sa couleur),
        // il y a un voisin à deux cases à gauche de s.
        if (s[1] > 0 && matrice[s[0]][s[1] - 1].equals(c.nomArrete()))
            voisins.add(new int[]{s[0], s[1] - 2});

        // Voisin deux cases à droite de s
        if (s[1] < 2 * taille && matrice[s[0]][s[1] + 1].equals(c.nomArrete()))
            voisins.add(new int[]{s[0], s[1] + 2});

        // Voisin deux cases au-dessus de s
        if (s[0] > 0 && matrice[s[0] - 1][s[1]].equals(c.nomArrete()))
            voisins.add(new int[]{s[0] - 2, s[1]});

        // Voisin deux cases en dessous de s
        if (s[1] < 2 * taille && matrice[s[0] + 1][s[1]].equals(c.nomArrete()))
            voisins.add(new int[]{s[0] + 2, s[1]});

        return voisins;
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
}