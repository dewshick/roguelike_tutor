package roguetutorial.creatures;

import asciiPanel.AsciiPanel;
import roguetutorial.World;
import roguetutorial.creatures.ai.PlayerAi;

/**
 * Created by avyatkin on 21/02/16.
 */
public class CreatureFactory {
    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Creature newPlayer() {
        Creature player = new Creature(world, '@', AsciiPanel.brightWhite);
        world.addAtEmptyNewLocation(player);
        new PlayerAi(player);
        return player;
    }
}
