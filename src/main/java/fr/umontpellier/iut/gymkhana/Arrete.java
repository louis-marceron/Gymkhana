package fr.umontpellier.iut.gymkhana;

public class Arrete {
   private Point a;
   private Point b;

    public Arrete(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }
}