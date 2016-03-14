package com.ekapeli.object;

import com.ekapeli.framework.GameObject;
import com.ekapeli.framework.ObjectID;
import com.ekapeli.framework.Texture;
import com.ekapeli.window.Animation;
import com.ekapeli.window.Game;
import com.ekapeli.window.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Enemy extends GameObject {

    private static final int width = 40, height = 50;
    private float gravity = 5.0f;

    Handler handler;

    Texture tex = Game.getInstance();
    
    private Animation enemyIdle;

    public Enemy(float x, float y, Handler handler, ObjectID id) {
        super(x, y, id);
        this.handler = handler;
        
        enemyIdle = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[2], 
        tex.enemy[3], tex.enemy[4], tex.enemy[5], tex.enemy[6], tex.enemy[7]);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        
        y += gravity;
        
        
        Collision(object);
        enemyIdle.runAnimation();
    }

    public void Collision(LinkedList<GameObject> object){
        for (int i = 0; i < handler.object.size(); i++) {
            
            GameObject tempObject = handler.object.get(i);
            
            if (tempObject.getId() == ObjectID.Block) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y= tempObject.getY() + 36;
                    velY = 0;
                }
            
                if (getBounds().intersects(tempObject.getBounds())) {
                    y= tempObject.getY() - height;
                    velY = 0;
                    
                    jumping = false;
                    falling = false;
                }else{
                    falling = true;
                }
                
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 35;
                }
                
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - width;
                }
            }
            
            if (tempObject.getId() == ObjectID.Bullet) {
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                }
                
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                   handler.removeObject(tempObject);
                    handler.removeObject(this);
                }
                
            }
        }
    }
    
    public void render(Graphics g) {
       enemyIdle.drawAnimation(g, (int)x, (int)y);
       Graphics2D g2d = (Graphics2D) g;

//        g.setColor(Color.red);
//        g2d.draw(getBounds());
//        g2d.draw(getBoundsTop());
//        g2d.draw(getBoundsLeft());
//        g2d.draw(getBoundsRight());
    }

    
     public Rectangle getBounds() {
        return new Rectangle((int) x + (int) (width / 4), (int) y + (int) height / 2, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) x + (int) (width / 4), (int) y, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) x + (int) width - 6, (int) y + 5, (int) 5, (int) height - 10);
    }
    

}
