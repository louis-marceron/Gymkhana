package fr.umontpellier.iut.gymkhana;

import java.util.List;

public class Plateau {
    Graphe grapheRouge;
    Graphe grapheBlanc;
    Joueur joueurRouge;
    Joueur joueurBlanc;
    int taille;

    public Plateau(int taille) {
        this.grapheBlanc = new Graphe(taille+1,taille,'B');
        this.grapheRouge = new Graphe(taille,taille+1,'R');
        this.joueurRouge = new Joueur();
        this.joueurBlanc = new Joueur();
        this.taille = taille;
    }

    @Override
    public String toString() {
        int x1;
        int x2;
        int y1;
        int y2;
        String str="";
        char[][] tab = new char[taille*2][taille*2];
        for (int i = 0; i < tab.length ; i++) {
            for (int j = 0; j < tab[i].length ; j++) {
                tab[i][j]='0';
            }
        }
        for (Arrete a:grapheBlanc.getArrete()) {
            x1=a.getA().getX();
            y1=a.getA().getY();
            x2=a.getB().getX();
            y2=a.getB().getY();
            tab[x1+x2][y1+y2+1] = a.getA().couleur;
        }
        for (Arrete a:grapheRouge.getArrete()) {
            x1=a.getA().getX();
            y1=a.getA().getY();
            x2=a.getB().getX();
            y2=a.getB().getY();
            tab[x1+x2+1][Math.max(y1,y2)+2] = a.getA().couleur;
        }

        for (int i = 0; i < tab.length ; i++) {
            for (int j = 0; j < tab[i].length ; j++) {
               str+= tab[i][j] + "\t";
            }
            str+="\n";
        }
       return str;
    }

    public Graphe getGrapheRouge() {
        return grapheRouge;
    }

    public Graphe getGrapheBlanc() {
        return grapheBlanc;
    }

    public List<Point> getVoisinsPossibles() {
        // TODO implement here
        return null;
    }
}