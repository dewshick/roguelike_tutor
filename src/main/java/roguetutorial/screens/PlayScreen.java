package roguetutorial.screens;

import asciiPanel.AsciiPanel;
import roguetutorial.*;
import roguetutorial.creatures.Creature;
import roguetutorial.creatures.CreatureFactory;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by avyatkin on 20/02/16.
 */
public class PlayScreen implements Screen {

    private World world;
    private int screenWidth;
    private int screenHeight;
    private Creature player;
    private List<String> messages;

    public static final int PLAYSCREEN_HEIGHT = 21;

    public PlayScreen() {
        screenHeight = PLAYSCREEN_HEIGHT;
        screenWidth = MainApp.SCREEN_WIDTH;
        messages = new ArrayList<>();
        createWorld();
        CreatureFactory factory = new CreatureFactory(world);
        createCreatures(factory);
    }

    private void createCreatures(CreatureFactory factory) {
        player = factory.newPlayer(messages);
        for (int i = 0; i < 8; i++) {
            factory.newFungus();
        }
    }

    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();

        world.update();
        displayTiles(terminal, left, top);
        String stats = String.format(" %3d/%3d hp", player.getHp(), player.getMaxHp());
        terminal.write(stats, 1, 23);
        displayMessages(terminal, messages);
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

    final int LAST_DISPLAYED_MESSAGES = 4;

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = screenHeight + LAST_DISPLAYED_MESSAGES;
        int lastMsgId = messages.size() - 1;
        for (int i = 0; i < LAST_DISPLAYED_MESSAGES && lastMsgId > 0;i++) {
            terminal.writeCenter(messages.get(lastMsgId), top + i);
            lastMsgId--;
        }
    }

}
