package roguetutorial.creatures;

import asciiPanel.AsciiPanel;
import roguetutorial.world.World;
import roguetutorial.creatures.ai.FungusAi;
import roguetutorial.creatures.ai.PlayerAi;
import java.util.List;


/**
 * Created by avyatkin on 21/02/16.
 */
public class CreatureFactory {
    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Creature newPlayer(List<String> messages) {
        Creature player = new Creature(world, '@', AsciiPanel.brightWhite, 100, 20, 5);
        world.addAtEmptyLocation(player, 0);
        new PlayerAi(player, messages);
        return player;
    }

    public Creature newFungus(int z) {
        Creature fungus = new Creature(world, 'f', AsciiPanel.green, 10, 0, 0);
        world.addAtEmptyLocation(fungus, z);
        new FungusAi(fungus, this);
        return fungus;
    }
}
