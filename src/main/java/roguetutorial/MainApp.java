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

    public MainApp() {
        super();
        terminal = new AsciiPanel(100, 50);
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

    @Override
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
