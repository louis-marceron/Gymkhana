package fr.umontpellier.iut.gymkhana.model;

public class JoueurMinMax implements Joueur {
    private int[] bestMove;

    public JoueurMinMax() {
        this.bestMove = new int[2];
    }

    @Override
    public boolean jouer(Plateau plateau, Couleur couleur) {
        boolean b = false;
        do {
            minmax(plateau, 3, true, couleur, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            b = true;
            if (couleur == Couleur.Rouge && (bestMove[0] == 0 || bestMove[0] == 10)) {
                System.out.println("pb");
//                b = false;
            }
        } while (!b);
        plateau.afficherS(bestMove);
        plateau.getMatrice()[bestMove[0]][bestMove[1]] = new Arete(couleur);
        return plateau.joueurGagant(couleur);
    }


    public int minmax(Plateau plateau, int profondeur, boolean joueurMax, Couleur couleur, double alpha, double beta) {
        if (plateau.joueurGagant(couleur)) {
            return Integer.MAX_VALUE;
        } else if (plateau.joueurGagant(couleur == Couleur.Blanc ? Couleur.Rouge : Couleur.Blanc)){
            return Integer.MIN_VALUE;
        }
        if (profondeur == 0)
            return eval(plateau);
        if (joueurMax) {
            int maxEval = Integer.MIN_VALUE;

            for (int[] s : plateau.areteJouable(couleur)) {
                if (!(s[0] == 0 || s[0] == 10)) {
                    Plateau plateau1 = plateau.copie();
//                    System.out.println("DEBUG");
//                    System.out.println("{" + s[0] + "," + s[1] + "}");
//                    System.out.println("DEBUG");
                    plateau1.getMatrice()[s[0]][s[1]] = new Arete(couleur);
                    int eval = minmax(plateau1, profondeur - 1, false, couleur, alpha, beta);
//                maxEval = Math.max(maxEval, eval);
                    if (eval > maxEval) {
                        System.out.println("jsp");
                        maxEval = eval;
                        bestMove = s;
                    }
//                    alpha = Math.max(alpha, eval);
//                    if (beta <= alpha) break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            int compteur2 = 0;
            for (int[] s : plateau.areteJouable(couleur)) {
                System.out.println("compteur 2 " + compteur2++);
                if (!(s[1] == 0 || s[1] == 10)) {
//                    System.out.println("DEBUG");
//                    System.out.println("{" + s[0] + "," + s[1] + "}");
//                    System.out.println("DEBUG");
                    Plateau plateau1 = plateau.copie();
                    plateau1.getMatrice()[s[0]][s[1]] = new Arete(couleur);
                    int eval = minmax(plateau1, profondeur - 1, true, couleur, alpha, beta);
                    System.out.println("eval : " + eval);
//                minEval = Math.min(minEval, eval);
                    if (eval < minEval) {
                        System.out.println("jsp");
                        minEval = eval;
                        bestMove = s;
                    }
//                    alpha = Math.max(alpha, eval);
//                    if (beta <= alpha) break;
                }
            }
            return minEval;
        }
    }

    public int eval(Plateau plateau) {
        return (int) (Math.random() * 5);
    }
}
