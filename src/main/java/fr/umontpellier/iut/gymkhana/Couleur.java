package fr.umontpellier.iut.gymkhana;

public enum Couleur {
    Rouge, Blanc;

    public char toChar() {
        return this == Rouge ? 'R' : 'B';
    }
}
