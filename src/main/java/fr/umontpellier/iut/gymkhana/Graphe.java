package fr.umontpellier.iut.gymkhana;

import java.util.*;

public class Graphe {

    private List<Sommet> listSommet;
    private int hauteur;
    private int largeur;

    public Graphe(int hauteur, int largeur, char couleur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.listSommet = new ArrayList<>();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                listSommet.add(new Sommet(j,i,couleur));
            }
        }
    }

    public List<Sommet> getListSommets() {
        return listSommet;
    }

    @Override
    public String toString() {
        int comteur = 0;
        String str = "";
        for (int i = 0; i < listSommet.size(); i++) {
            if (comteur == largeur){
                System.out.println();
                comteur = 0;
            }
            System.out.print(listSommet.get(i).toString() + "\t");
            comteur++;
        }
        return str;
    }

    public ArrayList<Arrete> getArrete(){
        ArrayList<Arrete> list = new ArrayList<>();
        for (Sommet p1: listSommet) {
            for (Sommet p2: p1.getVoisins()) {
                list.add(new Arrete(p1,p2));
            }
        }
        return list;
    }

    public boolean ajouteArrete(Sommet x, Sommet y) {
        //TODO il faut regarder si l'arêtes est valide (si l'autre graphe ne la bloque pas)
        x.addVoisin(y);
        y.addVoisin(x);
        return true;
    }

    public Sommet getPointCord(int x, int y){
        for (Sommet p: listSommet) {
            if (p.getX() == x && p.getY() == y) return p;
        }
        return null;
    }

    public boolean verifieGagnant(Arrete ar) {
        //TODO
       return false;
    }

}