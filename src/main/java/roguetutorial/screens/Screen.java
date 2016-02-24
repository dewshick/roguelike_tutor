package roguetutorial.screens;
import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

/**
 * Created by avyatkin on 20/02/16.
 */
public interface Screen {
    static final int SCREEN_Y_LOWER_BOUND = 22;

    public void displayOutput(AsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);
}
