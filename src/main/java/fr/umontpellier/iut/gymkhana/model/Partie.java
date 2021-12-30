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

//        int a = entree.nextInt();
        int a = 4;
        if (a == 1) {
            j1 = new JoueurHumain();
            j2 = new JoueurMinMax();
        } else if (a == 2) {
            j1 = new JoueurHumain();
            j2 = new JoueurHumain();
        } else if (a == 3){
            j1 = new JoueurMinMax();
            j2 = new JoueurHumain();
        } else if (a == 4){
            j1 = new JoueurIADebutant();
            j2 = new JoueurMinMax();
        }
        System.out.println(plateau);
        run();
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

                Boolean b = joueur.jouer(plateau, couleur);
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
