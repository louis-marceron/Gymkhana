package fr.umontpellier.iut.gymkhana;

import java.util.*;

public class Graphe {

    private List<Point> listPoint;
    private int hauteur;
    private int largeur;

    public Graphe(int hauteur, int largeur, char couleur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.listPoint = new ArrayList<>();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                listPoint.add(new Point(j,i,couleur));
            }
        }
    }

    public List<Point> getListPoint() {
        return listPoint;
    }

    @Override
    public String toString() {
        int comteur = 0;
        String str = "";
        for (int i = 0; i < listPoint.size(); i++) {
            if (comteur == largeur){
                System.out.println();
                comteur = 0;
            }
            System.out.print(listPoint.get(i).toString() + "\t");
            comteur++;
        }
        return str;
    }

    public ArrayList<Arrete> getArrete(){
        ArrayList<Arrete> list = new ArrayList<>();
        for (Point p1:listPoint) {
            for (Point p2: p1.getVoisins()) {
                list.add(new Arrete(p1,p2));
            }
        }
        return list;
    }

    public boolean ajouteArrete( Point x,  Point y) {
        //TODO il faut regarder si l'arêtes est valide (si l'autre graphe ne la bloque pas)
        x.addVoisin(y);
        y.addVoisin(x);
        return true;
    }

    public boolean verifieGagnant(Arrete ar) {
        //TODO
       return false;
    }

}