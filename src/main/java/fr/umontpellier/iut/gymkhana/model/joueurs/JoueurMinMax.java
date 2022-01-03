package fr.umontpellier.iut.gymkhana.model.joueurs;

import fr.umontpellier.iut.gymkhana.model.Couleur;
import fr.umontpellier.iut.gymkhana.model.Plateau;
import fr.umontpellier.iut.gymkhana.model.pieces.Arete;
import fr.umontpellier.iut.gymkhana.model.pieces.PieceColoree;
import fr.umontpellier.iut.gymkhana.model.pieces.Sommet;

import java.util.ArrayList;

public class JoueurMinMax extends Joueur {
    private int[] bestMove;

    public JoueurMinMax(Plateau plateau, Couleur couleur) {
        super(plateau, couleur);
        this.bestMove = new int[2];
    }

    public void jouer() {
        minmax(getPlateau(), 2, true, getCouleur(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        getPlateau().getMatrice()[bestMove[0]][bestMove[1]] = new Arete(getCouleur());
    }

    public int minmax(Plateau plateau, int profondeur, boolean joueurMax, Couleur couleur, double alpha, double beta) {
        if (plateau.estGagnant(couleur)) {
            return Integer.MAX_VALUE;
        } else if (plateau.estGagnant(couleur == Couleur.Blanc ? Couleur.Rouge : Couleur.Blanc)) {
            return Integer.MIN_VALUE;
        }
        if (profondeur == 0) {
            int a = eval(plateau, couleur);
            return joueurMax ? a : -a;
        }

        if (joueurMax) {
            int maxEval = Integer.MIN_VALUE;
            for (int[] s : plateau.areteJouable(couleur)) {
                if (!(s[1] == 0 || s[1] == 10)) {
                    Plateau plateau1 = plateau.copie();
                    plateau1.getMatrice()[s[0]][s[1]] = new Arete(couleur);
                    int eval = minmax(plateau1, profondeur - 1, false, couleur == Couleur.Blanc ? Couleur.Rouge : Couleur.Blanc, alpha, beta);
                    if (eval > maxEval) {
                        maxEval = eval;
                        bestMove = s;
                    }
                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha) break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int[] s : plateau.areteJouable(couleur)) {
                if (!(s[0] == 0 || s[0] == 10 || s[1] == 0 || s[1] == 10)) {
                    Plateau plateau1 = plateau.copie();
                    plateau1.getMatrice()[s[0]][s[1]] = new Arete(couleur);
                    int eval = minmax(plateau1, profondeur - 1, true, couleur == Couleur.Blanc ? Couleur.Rouge : Couleur.Blanc, alpha, beta);

                    if (eval < minEval) {
                        minEval = eval;
                        bestMove = s;
                    }
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) break;
                }
            }
            return minEval;
        }
    }

    public int eval(Plateau plateau, Couleur couleur) {
        int max = Integer.MIN_VALUE;
        int cur;
        int[] s = new int[2];
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < plateau.getMatrice().length; i++) {
            for (int j = 0; j < plateau.getMatrice()[i].length; j++) {
                if (plateau.getMatrice()[i][j].getClass().equals(Sommet.class)) {
                    if (((PieceColoree)plateau.getMatrice()[i][j])  .getCouleur().equals(couleur)) {
                        s[0] = i;
                        s[1] = j;
                        cur = plateau.connex(s, couleur, list).size();
                        max = Math.max(max, cur);
                    }
                }
            }
        }
        return max;
    }
}
