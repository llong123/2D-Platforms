package com.ekapeli.framework;

import com.ekapeli.window.BufferedImageLoader;
import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs, ps, bus,es;

    BufferedImageLoader loader;

    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage bullet_sheet = null;
    private BufferedImage enemy_sheet = null;

    public BufferedImage[] block = new BufferedImage[3];
    public BufferedImage[] player = new BufferedImage[22];
    public BufferedImage[] bullet = new BufferedImage[2];
    public BufferedImage[] enemy = new BufferedImage[8];

    public Texture() {

        loader = new BufferedImageLoader();

        try {
            block_sheet = loader.loadImage("/block_sheet.png");
            player_sheet = loader.loadImage("/player_sheet2.png");
            bullet_sheet = loader.loadImage("/bullet.png");
            enemy_sheet = loader.loadImage("/enemy_sheet.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        bus = new SpriteSheet(bullet_sheet);
        es = new SpriteSheet(enemy_sheet);

        getTexture();
    }

    private void getTexture() {
        //Blocks
        block[0] = bs.grabImage(1, 1, 32, 32);
        block[1] = bs.grabImage(2, 1, 32, 32);
        block[2] = bs.grabImage(3, 1, 32, 32);

        //Player's animation
        //Looking right
        player[0] = ps.grabImage(1, 1, 40, 40);
        player[1] = ps.grabImage(2, 1, 40, 40);
        player[2] = ps.grabImage(3, 1, 40, 40);
        player[3] = ps.grabImage(4, 1, 40, 40);
        player[4] = ps.grabImage(5, 1, 40, 40);
        player[5] = ps.grabImage(6, 1, 40, 40);
        player[6] = ps.grabImage(7, 1, 40, 40);
        player[7] = ps.grabImage(8, 1, 40, 40);
        player[8] = ps.grabImage(9, 1, 40, 40);
        player[9] = ps.grabImage(10, 1, 40, 40);

        //Looking left
        player[10] = ps.grabImage(1, 2, 40, 40);
        player[11] = ps.grabImage(2, 2, 40, 40);
        player[12] = ps.grabImage(3, 2, 40, 40);
        player[13] = ps.grabImage(4, 2, 40, 40);
        player[14] = ps.grabImage(5, 2, 40, 40);
        player[15] = ps.grabImage(6, 2, 40, 40);
        player[16] = ps.grabImage(7, 2, 40, 40);
        player[17] = ps.grabImage(8, 2, 40, 40);
        player[18] = ps.grabImage(9, 2, 40, 40);
        player[19] = ps.grabImage(10, 2, 40, 40);

        //Player Jumps
        player[20] = ps.grabImage(2, 3, 40, 40); // facing = 1
        player[21] = ps.grabImage(3, 3, 40, 40); // faceing = -1

        //Bullet
        bullet[0] = bus.grabImage(1, 1, 150, 150);
        bullet[1] = bus.grabImage(3, 1, 150, 150);
        
        //Enemy
        enemy[0] = es.grabImage(1, 1, 40, 50);
        enemy[1] = es.grabImage(2, 1, 40, 50);
        enemy[2] = es.grabImage(3, 1, 40, 50);
        enemy[3] = es.grabImage(4, 1, 40, 50);
        enemy[4] = es.grabImage(1, 2, 40, 50);
        enemy[5] = es.grabImage(2, 2, 40, 50);
        enemy[6] = es.grabImage(3, 2, 40, 50);
        enemy[7] = es.grabImage(4, 2, 40, 50);
    }

}
