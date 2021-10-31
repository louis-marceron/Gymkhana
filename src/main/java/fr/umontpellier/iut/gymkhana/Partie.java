package fr.umontpellier.iut.gymkhana;

import java.util.Scanner;

public class Partie {
    Plateau plateau;

    public void intit(){
        plateau = new Plateau(5);
        System.out.println(plateau);
        run();
    }

    private void run(){
        int x1, y1, x2, y2,continuer;
        Scanner entree = new Scanner(System.in);

        do {
            for (int k = 0; k < 2; k++) { // Boucle appellant les 2 joueurs
                Couleur couleur; // La couleur change en fonction de la valeur de k. Evite la redondance.
                if (k == 0)
                    couleur = Couleur.Blanc;
                else
                    couleur = Couleur.Rouge;


                System.out.println("Joueur " + couleur.nomCouleur() + ", choisissez votre coup à jouer");

                boolean b = true;

                do { // Tant que les points rentrés ne sont pas valides, impossible de jouer
                    if (!b) System.out.println("Points non valides, veuillez réessayer");
                    System.out.println("sélectionnez X et Y du premier point");
                    x1 = entree.nextInt();
                    y1 = entree.nextInt();
                    System.out.println("sélectionnez X et Y du deuxième point");
                    x2 = entree.nextInt();
                    y2 = entree.nextInt();
                    b = plateau.ajouterArrete(new int[]{x1, y1}, new int[]{x2, y2}, couleur);
                } while (!b);

                System.out.println("Votre coup à été joué !\n\n");
                System.out.println(plateau);
            }


            System.out.println("Tapez 1 pour continuer ou tapez 2 pour finir");
            continuer = entree.nextInt();

        } while (continuer != 2);
    }


    public static void main(String[] args) {
        Partie p = new Partie();
        p.intit();
    }
}
