package fr.umontpellier.iut.gymkhana;

import java.util.List;

public class Plateau {
    Graphe grapheRouge;
    Graphe grapheBlanc;
    Joueur joueurRouge;
    Joueur joueurBlanc;
    int taille;

    public Plateau(int taille) {
        this.grapheBlanc = new Graphe(taille + 1, taille, Couleur.Blanc);
        this.grapheRouge = new Graphe(taille, taille + 1, Couleur.Rouge);
        this.joueurRouge = new Joueur();
        this.joueurBlanc = new Joueur();
        this.taille = taille;
    }

    @Override
    public String toString() {
        int x1, x2, y1, y2;
        String str = "";
        char[][] tab = new char[taille * 2 + 1][taille * 2 + 1];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) tab[i][j] = ' ';
                    else tab[i][j] = '.';
                } else {
                    if (j % 2 == 0) tab[i][j] = '.';
                    else tab[i][j] = ' ';
                }
            }
        }
        for (Arrete a : grapheBlanc.getArrete()) {
            x1 = a.getA().getX();
            y1 = a.getA().getY();
            x2 = a.getB().getX();
            y2 = a.getB().getY();
            tab[x1 + x2][y1 + y2 + 1] = a.getA().couleur.toChar();
        }
        for (Arrete a : grapheRouge.getArrete()) {
            x1 = a.getA().getX();
            y1 = a.getA().getY();
            x2 = a.getB().getX();
            y2 = a.getB().getY();
            if (x1 == x2) tab[x1 + x2 + 1][Math.max(y1, y2)*2-1] = a.getA().couleur.toChar();
            else tab[x1 + x2 + 1][Math.max(y1, y2)*2] = a.getA().couleur.toChar();

        }

        int compteur = 0; //pour afficher les numéros des sommets
        int compteur2 = 0;
        for (int i = 0; i < tab.length; i++) {
            if (compteur2 == 1) {
                str += "\u001B[31m";
                str += compteur + "\t";
                str += "\u001B[0m";
                compteur2 = 0;
            } else{
                str += "\u001B[0m";
                str += compteur + "\t"; //numéroter les sommets axe horizontal
                compteur2 = 1;
            }

            if (i != 0 && i % 2 != 0) compteur += 1; //numéroter les sommets axe horizontal

            for (int j = 0; j < tab[i].length; j++) {
                if (i % 2 != 0 && j % 2 == 0) str += "\u001B[31m";
                if (i % 2 != 0 && j % 2 != 0) str += "\u001B[0m";
                if (i % 2 == 0) str += "\u001B[0m";
                str += tab[i][j] + "\t";
            }
            str += "\n";
            if (i == tab.length - 1) {
                str += " \t";
                compteur = 0;
                for (int j = 0; j < tab.length; j++) {
                    if (j % 2 == 0) str += "\u001B[31m";
                    else str += "\u001B[0m";
                    str += compteur + "\t";
                    if (j != 0 && j % 2 != 0) compteur += 1;
                }
            }
        }
        str += "\u001B[0m";
        return str;
    }

    public Graphe getGrapheRouge() {
        return grapheRouge;
    }

    public Graphe getGrapheBlanc() {
        return grapheBlanc;
    }

    public List<Sommet> getVoisinsPossibles() {
        // TODO implement here
        return null;
    }
}