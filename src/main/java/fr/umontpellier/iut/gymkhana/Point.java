package fr.umontpellier.iut.gymkhana;

import java.util.ArrayList;
import java.util.List;

public class Point {
    private int x;
    private int y;
    char couleur;
    private List<Point> voisins;

    public Point(int x, int y, char couleur) {
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

    public List<Point> getVoisins() {
        return voisins;
    }

    public void addVoisin(Point p){
        voisins.add(p);
    }

    @Override
    public String toString() {
        return "" + couleur;
    }
}