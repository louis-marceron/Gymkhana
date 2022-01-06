class Sommet{}

class Plateau{
    private couleur;
    getCouleur(){
        return this.couleur
    }
    connex(){
        return 1;
    }
}
function max(x,y){}
function eval (plateau, couleur){
    let max = -Infinity;
    let cur;
    for (i = 0; i <plateau.size; i++) {
        for (j = 0; j <plateau.size ; j++) {
            if (plateau[i][j] == Sommet)
                if (plateau[i][j].getCouleur() == couleur){
                    cur = plateau.connex().size
                    max = Math.max(max,cur)
                }
        }
    }
    return max
}