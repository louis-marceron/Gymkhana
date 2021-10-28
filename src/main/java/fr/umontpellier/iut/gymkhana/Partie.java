package fr.umontpellier.iut.gymkhana;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partie {
    Plateau plateau;

    public void intit(){
        plateau = new Plateau(5);
        run2();
    }

    private void run(){
        int x1, y1, x2, y2,continuer;
        Scanner entree = new Scanner(System.in);
        boolean b = true;
        while (b){
            System.out.print("\033[H\033[2J");
            System.out.println(plateau);
            System.out.println("Joueur Blanc, choisissez votre coup à jouer");
            System.out.println("sélectionnez X et Y du premier point");
            x1 = entree.nextInt();
            y1 = entree.nextInt();
            System.out.println("sélectionnez X et Y du deuxième point");
            x2 = entree.nextInt();
            y2 = entree.nextInt();
            plateau.getGrapheBlanc().getPointCord(x1,y1).addVoisin(plateau.getGrapheBlanc().getPointCord(x2,y2));
            System.out.println("Votre coup à été joué !\n\n");
            System.out.print("\033[H\033[2J");
            System.out.println(plateau);

            System.out.println("Joueur Rouge, choisissez votre coup à jouer");
            System.out.println("sélectionnez X et Y du premier point");
            x1 = entree.nextInt();
            y1 = entree.nextInt();
            System.out.println("sélectionnez X et Y du deuxième point");
            x2 = entree.nextInt();
            y2 = entree.nextInt();
            plateau.getGrapheRouge().getPointCord(x1,y1).addVoisin(plateau.getGrapheRouge().getPointCord(x2,y2));
            System.out.println("Votre coup à été joué !\n\n");
            System.out.print("\033[H\033[2J");
            System.out.println(plateau);


            System.out.println("Tapez 1 pour continuer \nou tapez 2 pour finir");
            continuer = entree.nextInt();
            if (continuer == 2) b = false;

        }
    }

    private void run2(){
        int continuer;
        Scanner entree = new Scanner(System.in);
        boolean b = true;
        while (b){
            System.out.print("\033[H\033[2J");
            System.out.println(plateau);
            System.out.println("Joueur Blanc, choisissez votre coup à jouer");
            plateau.jouer(plateau.getGrapheBlanc());
            System.out.println("Votre coup à été joué !\n\n");
            System.out.print("\033[H\033[2J");
            System.out.println(plateau);

            System.out.println("Joueur Rouge, choisissez votre coup à jouer");
           plateau.jouer(plateau.getGrapheRouge());
            System.out.println("Votre coup à été joué !\n\n");
            System.out.print("\033[H\033[2J");
            System.out.println(plateau);


            System.out.println("Tapez 1 pour continuer \nou tapez 2 pour finir");
            continuer = entree.nextInt();
            if (continuer == 2) b = false;

        }
    }

    public void voirVoissinsP(){
        System.out.println(plateau);

        Graphe g = plateau.getGrapheBlanc();
        Sommet s = g.getPointCord(5,4);
        ArrayList<Sommet> v = g.voisinsPossibles(s);
        System.out.println("Voici avec quelles points vous pouvez jouer");
        for (Sommet s1 : v) {
            System.out.println(s1.afficherPoint());
        }
    }


    public static void main(String[] args) {

        Partie p = new Partie();
        p.intit();
    }
}