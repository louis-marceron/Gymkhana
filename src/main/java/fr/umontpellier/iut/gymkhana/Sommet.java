package fr.umontpellier.iut.gymkhana;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private int x;
    private int y;
    char couleur;
    private List<Sommet> voisins;

    public Sommet(int x, int y, char couleur) {
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

    public List<Sommet> getVoisins() {
        return voisins;
    }

    public void addVoisin(Sommet p){
        voisins.add(p);
    }

    @Override
    public String toString() {
        return "" + couleur;
    }
}