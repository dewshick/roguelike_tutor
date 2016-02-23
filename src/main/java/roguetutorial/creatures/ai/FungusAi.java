package roguetutorial.creatures.ai;

import roguetutorial.creatures.Creature;
import roguetutorial.creatures.CreatureFactory;
import roguetutorial.world.Point3D;

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
        Point3D displacement = new Point3D(
                (int)(Math.random() * 11) - 5,
                (int)(Math.random() * 11) - 5,
                0);
        Point3D newPlace = creature.coords.plus(displacement);
        if (!creature.canEnter(newPlace))
            return;

        Creature child = factory.newFungus(newPlace.z);
        child.coords = newPlace;
        spread++;
        creature.logAction("spawn a child");
    }
}
