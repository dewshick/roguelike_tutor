package roguetutorial.creatures.ai;

import roguetutorial.Tile;
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

    public void onEnter(int newX, int newY, Tile tile) { }

    public void onUpdate() {}

    public void onNotify(String format) { }
}
