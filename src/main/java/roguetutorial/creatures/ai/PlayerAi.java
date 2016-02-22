package roguetutorial.creatures.ai;

import roguetutorial.Tile;
import roguetutorial.creatures.Creature;

import java.util.List;

/**
 * Created by avyatkin on 21/02/16.
 */
public class PlayerAi extends CreatureAi {
    public PlayerAi(Creature creature, List<String> messages) {
        super(creature);
        this.messages = messages;
    }

    List<String> messages;



    public void onEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            creature.x = x;
            creature.y = y;
        } else if (tile.isDiggable()) {
            creature.dig(x, y);
        }
    }

    public void onNotify(String message) {
        messages.add(message);
    }
}
