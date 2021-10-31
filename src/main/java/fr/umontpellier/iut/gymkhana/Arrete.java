package fr.umontpellier.iut.gymkhana;

import java.util.Objects;

public class Arrete {
    @Override
    public String toString() {
        return "Arrete{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    private Sommet a;
    private Sommet b;
    private Couleur couleur;

    public Arrete(Sommet a, Sommet b) {
        this.a = a;
        this.b = b;
        couleur = a.getCouleur();
    }

    public Sommet getA() {
        return a;
    }

    public Sommet getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arrete arrete = (Arrete) o;
        return (Objects.equals(a, arrete.a) && Objects.equals(b, arrete.b)) || (Objects.equals(b, arrete.a) && Objects.equals(a, arrete.b));
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}