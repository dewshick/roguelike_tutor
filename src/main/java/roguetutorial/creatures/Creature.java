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

    private int maxHp;
    private int hp;
    private int attackValue;
    private int defenseValue;

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public Color getColor() { return color; }
    public char getGlyph() { return glyph; }
    public void setAi(CreatureAi ai) { this.ai = ai; }

    public Creature(World world, char glyph, Color color, int maxHp, int attackValue, int defenseValue) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
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
        int strikeDamage = Math.max(0, getAttackValue() - enemy.getDefenseValue());
        strikeDamage = (int)(strikeDamage * Math.random()) + 1;
        notify("You attack the '%s' for %d damage", enemy.getGlyph(), strikeDamage);
        enemy.modifyHp(-strikeDamage);
        enemy.notify("The '%s' attacks you for %d damage.", glyph, strikeDamage);
    }

    private void modifyHp(int i) {
        hp += i;
        if (hp < 1) {
            world.remove(this);
            logAction("die");
        }
    }

    public void update() {
        ai.onUpdate();
    }

    public boolean canEnter(int x, int y) {
        return world.tile(x,y).isGround() && !world.creatureAt(x, y).isPresent();
    }

    public void notify(String message, Object ... params) {
        ai.onNotify(String.format(message, params));
    }

    public void logAction(String message, Object ... params) {
        int radius = 9;
        for(int ox = -radius; ox < radius + 1; ox++)
            for(int oy = -radius; oy < radius + 1; oy++) {
                if(ox * ox + oy * oy > radius * radius)
                    continue;

                Optional<Creature> maybeReciever = world.creatureAt(x + ox, y + oy);
                if (!maybeReciever.isPresent())
                    continue;

                Creature reciever = maybeReciever.get();
                if (reciever == this)
                    reciever.notify("You " + message + ".", params);
                else
                    reciever.notify(String.format("The '%s' %s", glyph, makeSecondPerson(message)), params);
            }
    }

    private String makeSecondPerson(String text){
        String[] words = text.split(" ");
        words[0] = words[0] + "s";

        StringBuilder builder = new StringBuilder();
        for (String word : words){
            builder.append(" ");
            builder.append(word);
        }

        return builder.toString().trim();
    }

}
