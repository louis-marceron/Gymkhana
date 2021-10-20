package fr.umontpellier.iut.gymkhana;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Graphe {

    private List<Sommet> listPoint;
    private int hauteur;
    private int largeur;

    public Graphe(int hauteur, int largeur, Couleur c) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.listPoint = new ArrayList<>();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                listPoint.add(new Sommet(j,i,c));
            }
        }
    }

    public List<Sommet> getListPoint() {
        return listPoint;
    }

    @Override
    public String toString() {
        int compteur = 0;
        String str = "";
        for (Sommet sommet : listPoint) {
            if (compteur == largeur) {
                System.out.println();
                compteur = 0;
            }
            System.out.print(sommet.toString() + "\t");
            compteur++;
        }
        return str;
    }

    public ArrayList<Arrete> getArrete(){
        ArrayList<Arrete> list = new ArrayList<>();
        for (Sommet p1:listPoint) {
            for (Sommet p2: p1.getVoisins()) {
                list.add(new Arrete(p1,p2));
            }
        }
        return list;
    }

    public boolean ajouteArrete( Sommet x,  Sommet y) {
        //TODO il faut regarder si l'arêtes est valide (si l'autre graphe ne la bloque pas)
        x.addVoisin(y);
        y.addVoisin(x);
        return true;
    }

    public Sommet getPointCord(int x,int y){
        for (Sommet p: listPoint) {
            if (p.getX() == x && p.getY() == y) return p;
        }
        return null;
    }

    public List<Sommet> voisinsP(Sommet a){
        List<Sommet> voisinsPossibles = new ArrayList<>();
            if (a.getX() == 0) {
                voisinsPossibles.add(getPointCord(a.getX() + 1, a.getY()));
            } else {
                if (a.getX() == hauteur) {
                    voisinsPossibles.add(getPointCord(a.getX() - 1, a.getY()));
                } else {
                    voisinsPossibles.add(getPointCord(a.getX() - 1, a.getY()));
                    voisinsPossibles.add(getPointCord(a.getX() + 1, a.getY()));
                }
            }
            if (a.getY() == 0) {
                voisinsPossibles.add(getPointCord(a.getX(), a.getY() + 1));
            } else {
                if (a.getY() == largeur){
                    voisinsPossibles.add(getPointCord(a.getX(), a.getY() - 1));
                } else {
                    voisinsPossibles.add(getPointCord(a.getX(), a.getY() - 1));
                    voisinsPossibles.add(getPointCord(a.getX(), a.getY() + 1));
                }
            }
            if (a.getX() == hauteur) {
                voisinsPossibles.add(new Sommet(a.getX() - 1, a.getY(), a.getCouleur()));
            }

        return voisinsPossibles;
    }

    public boolean verifieGagnant(Arrete ar) {
        //TODO
       return false;
    }

}