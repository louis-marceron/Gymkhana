package fr.umontpellier.iut.gymkhana;

public class ImpressionPlateau {

    public static String impression(Plateau p) {
        StringBuilder str = new StringBuilder();
        String[][] tab = p.getMatrice();


        // Indices de l'axe horizontal en haut
        str.append("   ");
        for (int i = 0; i < tab.length; i++) {
            str.append(i).append("   ");
        }
        str.append("\n");


        // Boucle remplaçant les strings de la matrice par d'autres strings, plus lisibles
        for (int i = 0; i < tab.length; i++) {
            // Indices de l'axe horizontal
            str.append("\u001B[0m").append(i);
            if (i < 10)
                str.append("  ");
            else str.append(" ");



            // Valeurs de la matrice
            for (int j = 0; j < tab[i].length; j++) {
                if (i % 2 != 0 && j % 2 == 0) str.append("\u001B[31m"); // Met les sommets rouges en rouge
                switch (tab[i][j]) {
                    case "V":
                    case "NA":
                        str.append("    ");
                        break;
                    case "SB":
                    case "SR":
                        str.append(".   ");
                        break;
                    case "AB":
                        str.append("B   ");
                        break;
                    case "AR":
                        str.append("R   ");
                        break;
                }
            }

            str.append("\n");
        }

        // Indices de l'axe horizontal en bas
        str.append("   ");
        for (int i = 0; i < tab.length; i++) {
            str.append(i).append("   ");
        }
        str.append("\n");


        return str.toString();
    }
}
