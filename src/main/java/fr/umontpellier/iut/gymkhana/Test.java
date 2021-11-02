package fr.umontpellier.iut.gymkhana;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.Environment;

import java.awt.geom.Rectangle2D;

public class Test {

    public static void main(String[] args) {
        Environment env = new Environment()
        Plateau p = new Plateau(5);
        p.getGrapheBlanc().getPointCord(3,3).addVoisin(p.getGrapheBlanc().getPointCord(2,3));
        p.getGrapheRouge().getPointCord(3,3).addVoisin(p.getGrapheRouge().getPointCord(2,3));
        Game.graphics().renderShape(p, );
        Game.init(args);
        Game.start();
    }
}

