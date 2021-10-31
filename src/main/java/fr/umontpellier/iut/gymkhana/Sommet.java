package fr.umontpellier.iut.gymkhana;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sommet {
    private int x;
    private int y;
    Couleur couleur;
    private ArrayList<Sommet> voisins;

    public Sommet(int x, int y, Couleur couleur) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
        voisins = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Couleur getCouleur(){ return couleur;}

    public ArrayList<Sommet> getVoisins() {
        return voisins;
    }

    public void addVoisin(Sommet p){
        voisins.add(p);
    }

    @Override
    public String toString() {
//        return "" + couleur;
        return "(" + x + "," + y + ")";
    }

    public String afficherPoint() { // affichage du sommet par ses coordonnées
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sommet sommet = (Sommet) o;
        return x == sommet.x && y == sommet.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}