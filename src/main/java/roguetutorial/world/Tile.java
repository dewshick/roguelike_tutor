package roguetutorial.world;

import asciiPanel.AsciiPanel;
import roguetutorial.Drawable;

import java.awt.*;

/**
 * Created by avyatkin on 20/02/16.
 */
public enum Tile implements Drawable {
    FLOOR('.', AsciiPanel.yellow),
    WALL('#', AsciiPanel.yellow),
    BOUNDS('x', AsciiPanel.brightBlack),
    STAIRS_UP('<', AsciiPanel.brightBlack),
    STAIRS_DOWN('>', AsciiPanel.brightBlack);

    private char glyph;
    private Color color;

    public char getGlyph() {
        return glyph;
    }

    public Color getColor() {
        return color;
    }

    Tile(char x, Color brightBlack) {
        glyph = x;
        color = brightBlack;
    }

    public boolean isDiggable() { return this == Tile.WALL; }

    public boolean isGround() { return this != Tile.BOUNDS && this != Tile.WALL; }
}
