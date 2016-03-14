package com.ekapeli.object;

import com.ekapeli.framework.GameObject;
import com.ekapeli.framework.ObjectID;
import com.ekapeli.framework.Texture;
import com.ekapeli.window.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bullet extends GameObject {

    Texture tex = Game.getInstance();
    private int speed;

    private int width, height, cwidth, cheight;

    public Bullet(float x, float y, int speed, ObjectID id) {
        super(x, y, id);
        this.speed = speed;

        width = 15;
        height = 15;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += speed;
    }

    @Override
    public void render(Graphics g) {
        if (facing == 1) {
            g.drawImage(tex.bullet[0], (int) x, (int) y, width, height, null);
        } else if (facing == -1) {
            g.drawImage(tex.bullet[1], (int) x, (int) y, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 15, 15);
    }

}
