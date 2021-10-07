package fr.umontpellier.iut.gymkhana;

public class Partie {
    public static void main(String[] args) {

       Plateau plateau = new Plateau(5);

       plateau.getGrapheBlanc().getPointCord(3,3).addVoisin(plateau.getGrapheBlanc().getPointCord(2,3));
        System.out.println(plateau.toString());
    }
}