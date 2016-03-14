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

public class Player extends GameObject {

    public static final float MAX_SPEED = 10;
    private float width = 40, height = 40;
    private float gravity = 0.5f;
    
    private int maxHealth = 100;
    private int currentHealth = 100;
    
    
    Handler handler;
    
    Texture tex = Game.getInstance();
    
    private Animation playerLeft;
    private Animation playerRight;

    public Player(float x, float y, Handler handler, ObjectID id) {
        super(x, y, id);
        this.handler = handler;
        
        playerRight = new Animation(4, tex.player[1], tex.player[2], tex.player[3], 
        tex.player[4], tex.player[5], tex.player[6], tex.player[7], tex.player[8], 
        tex.player[9]);
        
        playerLeft = new Animation(4, tex.player[18], tex.player[17], tex.player[16], 
        tex.player[15], tex.player[14], tex.player[13], tex.player[12], tex.player[11], 
        tex.player[10]);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if (velX > 0) facing = 1;
        else if(velX < 0) facing = -1;
         
        if (falling || jumping) {
            velY += gravity;
        }

        if (velY > MAX_SPEED) {
            velY = MAX_SPEED;
        }

        Collision(object);
        playerLeft.runAnimation();
        playerRight.runAnimation();
        
    }

    private void Collision(LinkedList<GameObject> object) {
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
        }
    }

    @Override
    public void render(Graphics g) {
        
        
        if (jumping) {
            if (facing == 1) {
                g.drawImage(tex.player[20], (int)x, (int) y, null);
            } 
            else if(facing == -1){
                g.drawImage(tex.player[21], (int)x, (int) y, null);
            }
        }else{
            if (velX != 0) {
                if (facing == -1) {
                    playerLeft.drawAnimation(g, (int)x, (int)y);
                }
                else if(facing == 1){
                    playerRight.drawAnimation(g, (int)x, (int)y);
                }
            }else{
                if (facing == 1) {
                    g.drawImage(tex.player[0], (int)x, (int)y, null);
                }
                else if(facing == -1){
                    g.drawImage(tex.player[19], (int)x, (int)y, null);
                }
            }
        }

//        g.setColor(Color.blue);
//        g.fillRect((int) x, (int) y, (int) width, (int) height);
//
//        Graphics2D g2d = (Graphics2D) g;
//
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
    
    public int getCurrentHealth(){
        return currentHealth;
    }
    public int getMaxHealth(){
        return maxHealth;
    }

}
