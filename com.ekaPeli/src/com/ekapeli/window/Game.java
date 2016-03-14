package com.ekapeli.window;

import com.ekapeli.framework.KeyInput;
import com.ekapeli.framework.ObjectID;
import com.ekapeli.framework.Texture;
import com.ekapeli.object.Block;
import com.ekapeli.object.Enemy;

import com.ekapeli.object.Player;
import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    // Static variables
    public static int WIDTH, HEIGHT;

    //Booleans
    private boolean running = false;
    
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage level = null, cloud = null;
   
    
    //Object
    public Handler handler;
    public Camera cam;
    public static Texture tex;
    public int apu = 0;
    
    public synchronized void start() {
        
        if (running) {
            return;
        }
        
        running = true;
        thread = new Thread(this);
        thread.start();
        
    }
    
    public void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        
        tex = new Texture();
        
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/level.png");
        cloud = loader.loadImage("/cloud_bg.png");
        
        handler = new Handler();
        cam = new Camera(0, 0);
        LoadImageLevel(level);
        
        this.addKeyListener(new KeyInput(handler));
        
    }
    
    public void run() {
        
        init();
        
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
                
            }
            
        }
        
    }
    
    public void tick() {
        handler.tick();
        
        for (int i = 0; i < handler.object.size(); i++) {
            
            if (handler.object.get(i).getId() == ObjectID.Player) {
                cam.tick(handler.object.get(i));
            }
        }
    }
    
    public void render() {
        bs = this.getBufferStrategy();
        
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        
        g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        ////// DRAW START //////
        g.setColor(new Color(127,215,245));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g2d.translate(cam.getX(), cam.getY()); //BEGIN OF CAMERA
        for (int i = 0; i < cloud.getWidth() * 10; i+= cloud.getWidth()) {
            apu++;
            if (apu%2 == 0) {
                g.drawImage(cloud, i, 50, this);
            }else{
                g.drawImage(cloud, i, 100, this);
            }
            
        }
        handler.render(g);
        
        g2d.translate(-cam.getX(), -cam.getY());
        ////// DRAW END //////
        g.dispose();
        bs.show();
    }
    
    private void LoadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        
        System.out.println("Width = " + w + "\nHeight = " + h);
        
        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                
                if (red == 255 && green == 255 & blue == 255) { //color: white
                    handler.addObject(new Block(xx * 32, yy * 32, 0, ObjectID.Block));
                }
                if (red == 127 && green == 127 & blue == 127) { //color: grey
                    handler.addObject(new Block(xx * 32, yy * 32, 1, ObjectID.Block));
                }
                if (red == 255 && green == 0 & blue == 0) { //color: red
                    handler.addObject(new Player(xx * 32, yy * 32, handler, ObjectID.Player));
                }
                if (red == 0 && green == 100 & blue == 0) { //color: grey
                    handler.addObject(new Enemy(xx * 32, yy * 32, handler, ObjectID.Enemy));
                }
                
                
                
            }
        }
        
    }
    
    public static Texture getInstance() {
        return tex;
    }
    
    public static void main(String[] args) {
        new Window(800, 600, "Kakka", new Game());
    }
    
}
