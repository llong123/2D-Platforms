
package com.ekapeli.window;

import com.ekapeli.framework.GameObject;
import com.ekapeli.framework.ObjectID;
import com.ekapeli.object.Block;
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    
    public LinkedList<GameObject> object = new LinkedList<>();
    
    GameObject tempObject;
    
    public void tick(){
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            
            tempObject.tick(object);
        }
        
    }
    
    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    
//    public void createLevel(){
//       for (int i = 200; i < 600; i+= 32) {
//            addObject(new Block (i, 400, ObjectID.Block));
//        }
//        
//        for (int i = 0; i < Game.HEIGHT +32 ; i+= 32) {
//            addObject(new Block(0 ,i,  ObjectID.Block));
//        }
//        
//        for (int i = 0; i < Game.WIDTH*2 + 32; i += 32) {
//            addObject(new Block(i, Game.HEIGHT - 32, ObjectID.Block));
//        }
//    }
}
