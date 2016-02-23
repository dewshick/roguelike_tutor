package roguetutorial.creatures.ai;

import roguetutorial.world.Point3D;
import roguetutorial.world.Tile;
import roguetutorial.creatures.Creature;

/**
 * Created by avyatkin on 21/02/16.
 */
public class CreatureAi {
    protected Creature creature;

    public CreatureAi(Creature creature) {
        this.creature = creature;
        creature.setAi(this);
    }

    public void onEnter(Point3D newCoords, Tile tile) { }

    public void onUpdate() {}

    public void onNotify(String format) { }
}
