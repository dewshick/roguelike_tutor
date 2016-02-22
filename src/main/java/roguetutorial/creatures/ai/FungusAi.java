package roguetutorial.creatures.ai;

import roguetutorial.creatures.Creature;
import roguetutorial.creatures.CreatureFactory;

/**
 * Created by avyatkin on 22/02/16.
 */
public class FungusAi extends CreatureAi {

    CreatureFactory factory;
    int spread;

    public FungusAi(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }

    public void onUpdate() {
        if (spread < 5 && Math.random() < 0.02)
            spread();
    }

    private void spread() {
        int x = creature.x + (int)(Math.random() * 11) - 5;
        int y = creature.y + (int)(Math.random() * 11) - 5;
        if (!creature.canEnter(x, y))
            return;

        Creature child = factory.newFungus();
        child.x = x;
        child.y = y;
        spread++;
        creature.logAction("spawn a child");
    }
}
