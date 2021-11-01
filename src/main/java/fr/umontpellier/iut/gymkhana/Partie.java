package fr.umontpellier.iut.gymkhana;

import java.util.Scanner;

/**
 * La classe {@code Partie} initialise un {@code Plateau} et fait avancer une partie de Gymkhana
 * en prenant et en traitant les valeurs entrées par les joueurs.
 */
public class Partie {
    Plateau plateau;

    public void intit(){
        plateau = new Plateau(5);
        System.out.println(plateau);
        run();
    }

    /**
     * Intermédiaire entre les joueurs et un {@code Plateau}.<br>
     * Demande à chaque joueur de rentrer tour par tour des arêtes dans un {@code Plateau}.
     *
     */
    private void run() {
        int x1, y1, x2, y2, continuer;
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
                    // TODO try/catch scanner
                    if (!b) System.out.println("Points non valides, veuillez réessayer");
                    System.out.println("sélectionnez X et Y du premier point");
                    x1 = entree.nextInt();
                    y1 = entree.nextInt();
                    System.out.println("sélectionnez X et Y du deuxième point");
                    x2 = entree.nextInt();
                    y2 = entree.nextInt();
                    b = plateau.ajouterArete(new int[]{x1, y1}, new int[]{x2, y2}, couleur);
                } while (!b); // Tant que les points entrés ne sont pas valides, impossible de continuer

                System.out.println("Votre coup à été joué !\n\n");
                System.out.println(plateau);
            }

            // Système temporaire
            System.out.println("Tapez 1 pour continuer ou tapez 2 pour finir");
            continuer = entree.nextInt();

        } while (continuer != 2);
    }

    public static void main (String[]args){
        Partie p = new Partie();
        p.intit();
    }
}
