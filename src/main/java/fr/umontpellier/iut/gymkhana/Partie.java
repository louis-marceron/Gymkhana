package fr.umontpellier.iut.gymkhana;

public class Partie {
    public static void main(String[] args) {

       Plateau plateau = new Plateau(5);

       plateau.getGrapheBlanc().getListPoint().get(2).addVoisin(plateau.getGrapheBlanc().getListPoint().get(3));
        System.out.println(plateau.toString());
    }
}