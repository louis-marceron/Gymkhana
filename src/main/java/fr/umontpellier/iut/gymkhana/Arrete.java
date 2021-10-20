package fr.umontpellier.iut.gymkhana;

public class Arrete {
   private Sommet a;
   private Sommet b;

    public Arrete(Sommet a, Sommet b) {
        this.a = a;
        this.b = b;
    }

    public Sommet getA() {
        return a;
    }

    public Sommet getB() {
        return b;
    }
}