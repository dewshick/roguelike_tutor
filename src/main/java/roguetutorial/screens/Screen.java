package roguetutorial.screens;
import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

/**
 * Created by avyatkin on 20/02/16.
 */
public interface Screen {
    public void displayOutput(AsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);
}
