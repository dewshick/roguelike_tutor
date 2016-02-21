package roguetutorial.creatures.ai;

import roguetutorial.Tile;
import roguetutorial.creatures.Creature;

/**
 * Created by avyatkin on 21/02/16.
 */
public class PlayerAi extends CreatureAi {
    public PlayerAi(Creature creature) {
        super(creature);
    }

    public void onEnter(int x, int y, Tile tile) {
        System.out.println("coords: " + creature.x + "," + creature.y);

        if (tile.isGround()) {
            creature.x = x;
            creature.y = y;
        } else if (tile.isDiggable()) {
            creature.dig(x, y);
        }
    }
}
