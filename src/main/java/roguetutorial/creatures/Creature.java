package roguetutorial.creatures;

import roguetutorial.Drawable;
import roguetutorial.World;
import roguetutorial.creatures.ai.CreatureAi;

import java.awt.*;
import java.util.Optional;

/**
 * Created by avyatkin on 21/02/16.
 */
public class Creature implements Drawable {
    private World world;
    public int x;
    public int y;

    private char glyph;
    private Color color;
    private CreatureAi ai;

    public Color getColor() { return color; }
    public char getGlyph() { return glyph; }
    public void setAi(CreatureAi ai) { this.ai = ai; }

    public Creature(World world, char glyph, Color color) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
    }


    public void dig(int wx, int wy) {
        world.dig(wx, wy);
    }

    public void moveBy(int mx, int my) {
        int newX = x + mx;
        int newY = y + my;
        Optional<Creature> enemy = world.creatureAt(newX, newY);
        if (enemy.isPresent())
            attack(enemy.get());
        else
            ai.onEnter(newX, newY, world.tile(newX, newY));
    }

    private void attack(Creature enemy) {
        world.remove(enemy);
    }

    public void update() {
        ai.onUpdate();
    }

    public boolean canEnter(int x, int y) {
        return world.tile(x,y).isGround() && !world.creatureAt(x, y).isPresent();
    }
}
