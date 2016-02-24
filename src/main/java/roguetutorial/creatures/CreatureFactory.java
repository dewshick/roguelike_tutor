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
        final int PLAYER_HP = 100;
        final int PLAYER_DMG = 20;
        final int PLAYER_DEFENSE = 5;
        final int STARTING_LEVEL = 0;
        Creature player = new Creature(world, '@', AsciiPanel.brightWhite, PLAYER_HP, PLAYER_DMG, PLAYER_DEFENSE);
        world.addAtEmptyLocation(player, STARTING_LEVEL);
        new PlayerAi(player, messages);
        return player;
    }

    public Creature newFungus(int zLevel) {
        final int FUNGUS_HP = 10;
        final int FUNGUS_DMG = 0;
        final int FUNGUS_DEFENSE = 0;
        Creature fungus = new Creature(world, 'f', AsciiPanel.green, FUNGUS_HP, FUNGUS_DMG, FUNGUS_DEFENSE);
        world.addAtEmptyLocation(fungus, zLevel);
        new FungusAi(fungus, this);
        return fungus;
    }
}
