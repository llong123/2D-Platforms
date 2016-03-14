package com.ekapeli.framework;

import com.ekapeli.object.Bullet;
import com.ekapeli.window.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectID.Player) {

                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                }

                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                }

                if (key == KeyEvent.VK_DOWN && !tempObject.isDashing()) {

                    if (tempObject.getFacing() == 1) {
                        tempObject.setVelX(10);
                        tempObject.setDashing(true);
                    } else if (tempObject.getFacing() == -1) {
                        tempObject.setVelX(-10);
                        tempObject.setDashing(true);
                    }
                }
                if (key == KeyEvent.VK_UP && !tempObject.isJumping()) {
                    tempObject.setJumping(true);
                    tempObject.setVelY(-15);
                }
                if (key == KeyEvent.VK_J) {
                    if (tempObject.getFacing() == 1) {
                        handler.addObject(new Bullet((int) tempObject.getX(), (int) tempObject.getY() + 15, 10, ObjectID.Bullet));
                    } else if (tempObject.getFacing() == -1) {
                        handler.addObject(new Bullet((int) tempObject.getX(), (int) tempObject.getY() + 15, -10, ObjectID.Bullet));
                    }
                }

            }
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectID.Player) {
                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_DOWN) {
                    tempObject.setVelX(0);
                    tempObject.setDashing(false);

                }
            }

        }
    }
}
