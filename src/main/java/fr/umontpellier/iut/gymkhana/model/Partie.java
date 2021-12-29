package fr.umontpellier.iut.gymkhana.model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe {@code Partie} initialise un {@code Plateau} et fait avancer une partie de Gymkhana
 * en prenant et en traitant les valeurs entrées par les joueurs.
 */
public class Partie {
    Plateau plateau;
    Joueur j1;
    Joueur j2;

    public static void main(String[] args) {
        Partie p = new Partie();
        p.init();
    }

    public void init() {
        plateau = new Plateau(5);
        Scanner entree = new Scanner(System.in);
        System.out.println("Jouer à 1 ou 2 ?");

        j1 = new JoueurHumain();
        if (entree.nextInt() == 1) {
            j2 = new JoueurIADebutant();
        } else {
            j2 = new JoueurHumain();
        }
        System.out.println(plateau);
        run2();
    }

    /**
     * Intermédiaire entre les joueurs et un {@code Plateau}.<br>
     * Demande à chaque joueur de rentrer tour par tour des arêtes dans un {@code Plateau}.
     */
    private void run() {
        int[] s1, s2;
        ArrayList<int[]> sommetJouables, sommetVoisinsP;
        int x1, y1, x2, y2;
        boolean continuer = true;
        Scanner entree = new Scanner(System.in);

        do {
            for (int k = 0; k < 2; k++) { // Boucle appellant les 2 joueurs
                Couleur couleur;
                // La couleur change en fonction de k
                if (k == 0)
                    couleur = Couleur.Blanc;
                else
                    couleur = Couleur.Rouge;

                System.out.println("Joueur " + couleur.nomCouleur() + ", choisissez votre coup à jouer");

                boolean b = true;
                do {
                    if (couleur == Couleur.Blanc) {
                        // TODO try/catch scanner
                        if (!b) System.out.println("Points non valides, veuillez réessayer");
                        System.out.println("sélectionnez Y et X du premier point");
                        x1 = entree.nextInt();
                        y1 = entree.nextInt();
                        s1 = new int[]{x1, y1};
                        System.out.println("sélectionnez Y et X du deuxième point");
                        x2 = entree.nextInt();
                        y2 = entree.nextInt();
                        b = plateau.ajouterArete(s1, new int[]{x2, y2}, couleur);
                    } else {
                        sommetJouables = plateau.getSommetJouables(couleur);
                        s1 = sommetJouables.get((int) (Math.random() * (sommetJouables.size())));
                        sommetVoisinsP = plateau.getVoisinsSommetPossible(s1, couleur);
                        s2 = sommetVoisinsP.get((int) (Math.random() * (sommetVoisinsP.size())));
                        b = plateau.ajouterArete(s1, s2, couleur);
                    }
                    if (b && plateau.gagnant(s1, couleur)) {
                        System.out.println("Joueur " + couleur.nomCouleur() + " à gagné !!!!");
                        System.out.println(plateau);
                        System.out.println("Joueur " + couleur.nomCouleur() + " à gagné !!!!");
                        continuer = false;
                        break;
                    }
                } while (!b); // Tant que les points entrés ne sont pas valides, impossible de continuer

                if (!continuer) break;
                System.out.println("Votre coup à été joué !\n\n");
                System.out.println(plateau);
            }


        } while (continuer);
        System.out.println("Tappez 1 pour relancer une partie !");
        if (entree.nextInt() == 1) init();
    }


    private void run2() {
        int[] s1, s2;
        ArrayList<int[]> sommetJouables, sommetVoisinsP;
        int x1, y1, x2, y2;
        boolean continuer = true;
        Scanner entree = new Scanner(System.in);

        do {
            for (int k = 0; k < 2; k++) { // Boucle appellant les 2 joueurs
                Couleur couleur;
                Joueur joueur;
                // La couleur change en fonction de k
                if (k == 0) {
                    couleur = Couleur.Blanc;
                    joueur = j1;
                } else {
                    couleur = Couleur.Rouge;
                    joueur = j2;
                }

                System.out.println("Joueur " + couleur.nomCouleur() + ", choisissez votre coup à jouer");

                boolean b = joueur.jouer(plateau, couleur);
                if (b) {
                    System.out.println("Joueur " + couleur.nomCouleur() + " à gagné !!!!");
                    System.out.println(plateau);
                    System.out.println("Joueur " + couleur.nomCouleur() + " à gagné !!!!");
                    continuer = false;
                    break;
                }
                System.out.println("Votre coup à été joué !\n\n");
                System.out.println(plateau);
            }


        } while (continuer);
        System.out.println("Tappez 1 pour relancer une partie !");
        if (entree.nextInt() == 1) init();
    }
}
