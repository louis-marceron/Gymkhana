package fr.umontpellier.iut.gymkhana;

import java.util.ArrayList;
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
        int x1, x2, y1, y2;
        String str="";
        char[][] tab = new char[taille*2+1][taille*2+1];
        for (int i = 0; i < tab.length ; i++) {
            for (int j = 0; j < tab[i].length ; j++) {
                if (i%2 ==0) {
                    if (j%2 == 0) tab[i][j]=' ';
                    else tab[i][j]='.';
                }
                else {
                    if (j%2 == 0) tab[i][j]='.';
                    else tab[i][j]=' ';
                }
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

    public List<Point> getVoisinsPossibles(Point a) {
        ArrayList<Point> voisinsPossibles = new ArrayList<Point>();
        if (a.getCouleur()=='B'){
            ArrayList<Arrete> arretes = grapheBlanc.getArrete();
        }else{
            ArrayList<Arrete> arretes = grapheRouge.getArrete();
        }
            if (a.getX()==0){
                voisinsPossibles.add(new Point(a.getX()+1,a.getY(),a.getCouleur()));
            }else{
                if (a.getX()==taille){
                    voisinsPossibles.add(new Point(a.getX()-1,a.getY(),a.getCouleur()));
                }else{
                    voisinsPossibles.add(new Point(a.getX()-1,a.getY(),a.getCouleur()));
                    voisinsPossibles.add(new Point(a.getX()+1,a.getY(),a.getCouleur()));
                }
            }
            if (a.getY()==0){
                voisinsPossibles.add(new Point(a.getX(),a.getY()+1,a.getCouleur()));
            }else{
                if (a.getY()==taille) {
                    voisinsPossibles.add(new Point(a.getX(), a.getY()-1, a.getCouleur()));
                }else{
                    voisinsPossibles.add(new Point(a.getX(),a.getY()-1,a.getCouleur()));
                    voisinsPossibles.add(new Point(a.getX(),a.getY()+1,a.getCouleur()));
                }
            }
            if (a.getX()==taille){
                voisinsPossibles.add(new Point(a.getX()-1,a.getY(),a.getCouleur()));
            }
        return voisinsPossibles;
    }
}