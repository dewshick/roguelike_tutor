package roguetutorial.creatures.ai;

import roguetutorial.world.Point3D;
import roguetutorial.world.Tile;
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



    public void onEnter(Point3D target, Tile tile) {
        if (tile.isGround()) {
            creature.coords = target;
        } else if (tile.isDiggable()) {
            creature.dig(target);
        }
    }

    public void onNotify(String message) {
        messages.add(message);
    }
}
