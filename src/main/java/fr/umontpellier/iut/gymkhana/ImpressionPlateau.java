package fr.umontpellier.iut.gymkhana;

public class ImpressionPlateau {

    public static String impression(Plateau p) {
        StringBuilder str = new StringBuilder();
        String[][] tab = p.getMatrice();


        // Indices de l'axe horizontal
        str.append("   ");
        for (int i = 0; i < tab.length; i++) {
            str.append(i).append("   ");
        }
        str.append("\n");


        // Boucle remplaçant les strings de la matrice par d'autres strings, plus lisibles
        for (int i = 0; i < tab.length; i++) {
            // Indices de l'axe horizontal
            str.append("\u001B[0m").append(i).append("  ");


            // Valeurs de la matrice
            for (int j = 0; j < tab[i].length; j++) {
                if (i % 2 != 0 && j % 2 == 0) str.append("\u001B[31m");
                if (i % 2 != 0 && j % 2 != 0) str.append("\u001B[0m");
                if (i % 2 == 0) str.append("\u001B[0m");
                if (tab[i][j] == null || tab[i][j].equals("NA"))
                    str.append("    ");
                else if (tab[i][j].equals("SB"))
                    str.append(".   ");
                else if (tab[i][j].equals("SR"))
                    str.append(".   ");
                else if (tab[i][j].equals("AB")) {
                    str.append("B   ");
                }
                else if (tab[i][j].equals("AR"))
                    str.append("R   ");
            }

            str.append("\n");
        }


        return str.toString();
    }
}
