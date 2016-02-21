package roguetutorial.screens;

import asciiPanel.AsciiPanel;
import roguetutorial.Drawable;
import roguetutorial.Tile;
import roguetutorial.World;
import roguetutorial.WorldBuilder;
import roguetutorial.creatures.Creature;
import roguetutorial.creatures.CreatureFactory;

import java.awt.event.KeyEvent;
import java.util.Optional;

/**
 * Created by avyatkin on 20/02/16.
 */
public class PlayScreen implements Screen {

    private World world;
    private int screenWidth;
    private int screenHeight;
    private Creature player;

    public PlayScreen() {
        screenHeight = 21;
        screenWidth = 80;
        createWorld();
        CreatureFactory factory = new CreatureFactory(world);
        createCreatures(factory);
    }

    private void createCreatures(CreatureFactory factory) {
        player = factory.newPlayer();
        for (int i = 0; i < 8; i++) {
            factory.newFungus();
        }
    }

    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();

        world.update();
        displayTiles(terminal, left, top);
//        terminal.write(player.getGlyph(), player.x - left, player.y - top, player.getColor());
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()){
            case KeyEvent.VK_H:
            case KeyEvent.VK_LEFT: player.moveBy(-1, 0); break;
            case KeyEvent.VK_L:
            case KeyEvent.VK_RIGHT: player.moveBy(1, 0); break;
            case KeyEvent.VK_K:
            case KeyEvent.VK_UP: player.moveBy(0, -1); break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J: player.moveBy(0, 1); break;
            case KeyEvent.VK_Y: player.moveBy(-1, -1); break;
            case KeyEvent.VK_U: player.moveBy(1, -1); break;
            case KeyEvent.VK_B: player.moveBy(-1, 1); break;
            case KeyEvent.VK_N: player.moveBy(1, 1); break;
            case KeyEvent.VK_ESCAPE: return new LoseScreen();
            case KeyEvent.VK_ENTER: return new WinScreen();
        }
        return this;
    }

    private void createWorld() {
        world = new WorldBuilder(90, 31).makeCaves().build();
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int wx = x + left;
                int wy = y + top;

                Optional<Creature> optCreature = world.creatureAt(wx, wy);
                if (optCreature.isPresent()) {
                    Creature creature = optCreature.get();
                    terminal.write(creature.getGlyph(), x, y, creature.getColor());
                } else {
                    Tile tile = world.tile(wx, wy);
                    terminal.write(tile.getGlyph(), x, y, tile.getColor());
                }
            }
        }
    }



    public int getScrollX() {
        return Math.max(0, Math.min(player.x - screenWidth / 2, world.getWidth() - screenWidth));
    }

    public int getScrollY() {
        return Math.max(0, Math.min(player.y - screenHeight / 2, world.getHeight() - screenHeight));
    }

}
