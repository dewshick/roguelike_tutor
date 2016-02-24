package roguetutorial.screens;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

/**
 * Created by avyatkin on 20/02/16.
 */
public class StartScreen implements Screen {
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("roguelike tutorial", 1, 1);
        terminal.writeCenter("-- press [enter] to start --", SCREEN_Y_LOWER_BOUND);

    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
