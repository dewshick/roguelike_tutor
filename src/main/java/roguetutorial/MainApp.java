package roguetutorial;
import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import roguetutorial.screens.Screen;
import roguetutorial.screens.StartScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by avyatkin on 20/02/16.
 */
public class MainApp extends JFrame implements KeyListener {
    private AsciiPanel terminal;
    private Screen screen;

    public static final int SCREEN_WIDTH = 80;
    public static final int SCREEN_HEIGHT = 30;

    public MainApp() {
        super();
        terminal = new AsciiPanel(SCREEN_WIDTH, SCREEN_HEIGHT);
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    public void keyTyped(KeyEvent e) { }

    public void keyReleased(KeyEvent e) { }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
