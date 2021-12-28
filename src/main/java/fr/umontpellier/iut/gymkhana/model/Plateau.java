package fr.umontpellier.iut.gymkhana.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * La classe {@code Plateau} permet de créer des instances d'un plateau du Gymkhana, modélisé
 * à travers une matrice de type {@code String[][]}. Chaque case de la matrice représente les sommets / arêtes
 * des graphes Rouge et Blanc, ou les cases vides où l'on peut poser une arête.
 * <p><b>Légende</b>
 * <blockquote>
 * NA = case non assignée (correspond aux 4 coins du plateau)<br>
 * V = case vide<br>
 * SR = sommet rouge<br>
 * SB = sommet blanc<br>
 * AR = arête rouge<br>
 * AB = arête blanche<br>
 * </blockquote>
 * <p>La classe {@code Plateau} comprend les méthodes pouvant placer des arêtes et vérifier si un
 * {@code Joueur} est gagnant. Il y aussi des méthodes spécifiques aux Graphes, pouvant par exemple
 * retourner les voisins d'un sommet ou sa classe de connexité.
 */
public class Plateau {
    private final int taille;
    private final String[][] matrice;

    /**
     * Initialise un nouvel objet {@code Plateau} en construisant une matrice comprenant les cases
     * non assignées "NA", les cases vides "V" et les sommets rouges et blancs "SR" / "SB".
     *
     * @param taille La longueur des graphes Rouge et Blanc. Leur largeur est de {@code taille - 1}
     */
    public Plateau(int taille) {
        this.taille = taille;

        int dimension = taille * 2 + 1;
        matrice = new String[dimension][dimension];

        // On remplit la matrice de "V"
        for (String[] strings : matrice) Arrays.fill(strings, "V");

        // On remplit les coins de "NA"
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

    /**
     * Ajoute une arête de couleur {@code c} positionnée entre deux sommets passés en paramètres.
     *
     * @param s1 Les coordonnées x,y du premier sommet
     * @param s2 Les coordonnées x,y du second sommet
     * @param c  La couleur de l'arête que l'on veut placer
     * @return {@code false} si le placement de l'arête est illégal
     */
    public boolean ajouterArete(int[] s1, int[] s2, Couleur c) {
        // TODO try/catch + tests unitaires
        // Vérifie que les indices ne sont pas trop grands
        if (s1[0] > taille * 2
                || s1[1] > taille * 2
                || s2[0] > taille * 2
                || s2[1] > taille * 2)
            return false;

        // Vérifie que les deux cases sont toutes les deux SR ou toutes les deux SB
        if (!matrice[s1[0]][s1[1]].equals(c.nomSommet())
                || !matrice[s2[0]][s2[1]].equals(c.nomSommet()))
            return false;

        // Vérifie qu'il y a une case d'écart entre les deux cases
        if (Math.abs(s1[0] - s2[0]) + Math.abs(s1[1] - s2[1]) != 2)
            return false;

        // Vérifie que l'arête est uniquement sur une case vide
        if (!matrice[(s1[0] + s2[0]) / 2][(s1[1] + s2[1]) / 2].equals("V"))
            return false;

        matrice[(s1[0] + s2[0]) / 2][(s1[1] + s2[1]) / 2] = c.nomArete();
        return true;
    }

    /**
     * Retourne la liste des coordonnées des sommets adjacents (voisins) au sommet passé en paramètres.
     *
     * @param s le sommet dont on veut connaître ses voisins
     * @param c la couleur du sommet {@code s}
     * @return Liste des coordonnées des voisins de {@code s}
     */
    public ArrayList<int[]> getVoisinsSommet(int[] s, Couleur c) {
        // TODO tests unitaires
        ArrayList<int[]> voisins = new ArrayList<>();

        // Regarde s'il y a un voisin à gauche du sommet s en regardant s'il y a une
        // arête de couleur c à gauche de s
        if (s[1] > 0 && matrice[s[0]][s[1] - 1].equals(c.nomArete()))
            voisins.add(new int[]{s[0], s[1] - 2});

        // Regarde s'il y a un voisin à droite
        if (s[1] < 2 * taille && matrice[s[0]][s[1] + 1].equals(c.nomArete()))
            voisins.add(new int[]{s[0], s[1] + 2});

        // Regarde s'il y a un voisin au dessus
        if (s[0] > 0 && matrice[s[0] - 1][s[1]].equals(c.nomArete()))
            voisins.add(new int[]{s[0] - 2, s[1]});

        // Regarde s'il y a un voisin en dessous
        if (s[0] < 2 * taille && matrice[s[0] + 1][s[1]].equals(c.nomArete()))
            voisins.add(new int[]{s[0] + 2, s[1]});

        return voisins;
    }

    public ArrayList<int[]> getVoisinsSommetPossible(int[] s, Couleur c) {
        ArrayList<int[]> voisins = new ArrayList<>();

        // Regarde s'il y a un voisin possible à gauche du sommet s en regardant s'il y a une
        // arête de couleur c à gauche de s
        if (s[1] > 0 && matrice[s[0]][s[1] - 1].equals("V"))
            voisins.add(new int[]{s[0], s[1] - 2});

        // Regarde s'il y a un voisin possible à droite
        if (s[1] < 2 * taille && matrice[s[0]][s[1] + 1].equals("V"))
            voisins.add(new int[]{s[0], s[1] + 2});

        // Regarde s'il y a un voisin possible au dessus
        if (s[0] > 0 && matrice[s[0] - 1][s[1]].equals("V"))
            voisins.add(new int[]{s[0] - 2, s[1]});

        // Regarde s'il y a un voisin possible en dessous
        if (s[0] < 2 * taille && matrice[s[0] + 1][s[1]].equals("V"))
            voisins.add(new int[]{s[0] + 2, s[1]});

        return voisins;
    }

    public ArrayList<int[]> getSommetJouables(Couleur c){
        ArrayList<int[]> list = new ArrayList<>();
        int[] x;
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j <matrice[i].length ; j++) {
                if (matrice[i][j].equals(c.nomSommet())){
                     x = new int[]{i,j};
                    if (!getVoisinsSommetPossible(x,c).isEmpty()) list.add(x);
                }
            }
        }
        return list;
    }

    /**
     * Retourne vrai ou faux en fonction du si oui ou non le sommet est gagant
     *
     * @param s le sommet dont on veut connaître s'il est gagnant ou non
     * @param c la couleur du sommet {@code s}
     * @return retourne un boolean}
     */
    public boolean gagnant(int[] s, Couleur c) {
        ArrayList<int[]> connex = new ArrayList<>();
        connex = connex(s,c,connex); // on obtient la classe de connexité du sommet s.
        boolean a = false;
        boolean b = false;
        switch (c) {
            case Rouge: // dans le cas des sommets rouges on regarde si sa classe de connexité contient des sommets
                for (int[] som:connex){
                    if(som[1] == 0) a = true;
                    if(som[1] == 10) b = true;
                }
                break;

            case Blanc:
                for (int[] som:connex){
                    if(som[0] == 0) a = true;
                    if(som[0] == 10) b = true;
                }
                break;
        }
        return a && b;
    }

    private  boolean contenir(ArrayList<int[]> l, int[] s){
        for (int[] sommet: l) {
            if (sommet[0] == s[0] && sommet[1] == s[1]) return true;
        }
        return false;
    }
    public ArrayList<int[]> connex(int[] s, Couleur c, ArrayList<int[]> l) {
        for (int[] sommet : getVoisinsSommet(s, c)) {
            if (!contenir(l,sommet)) {
                l.add(sommet);
                connex(sommet, c, l);
            }
        }
        return l;
    }

    public int getTaille() {
        return taille;
    }

    public String[][] getMatrice() {
        return matrice;
    }

    /* J'ai déplacé l'affichage de Plateau dans la classe Impression,
    car il me semble que ça respecte mieux le principe de responsabilité unique
    (pour moi ce n'est pas le rôle de Plateau de faire l'affichage dans la console)
    */
    @Override
    public String toString() {
        return ImpressionPlateau.toStringPlateau(this);
    }
}