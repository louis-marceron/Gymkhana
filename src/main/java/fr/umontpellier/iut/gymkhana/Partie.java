package fr.umontpellier.iut.gymkhana;

public class Partie {
    public static void main(String[] args) {

       Plateau plateau = new Plateau(5);

       plateau.getGrapheBlanc().getPointCord(3,3).addVoisin(plateau.getGrapheBlanc().getPointCord(2,3));
        plateau.getGrapheRouge().getPointCord(3,3).addVoisin(plateau.getGrapheRouge().getPointCord(2,3));
        System.out.println(plateau);


//        String str = "\u001B[31m" + "tedgz" + "\u001B[0m" + "dede";
//        System.out.println(str);
    }
}