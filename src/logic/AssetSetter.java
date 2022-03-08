package logic;

import object.OBJ_DirectionOrb;

import java.util.Random;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        Random rand = new Random();

        gp.obj[0] = new OBJ_DirectionOrb();
        gp.obj[0].x = rand.nextInt(gp.screenWidth - 6*gp.tileSize)+3*gp.tileSize;
        gp.obj[0].y = rand.nextInt(gp.screenHeight - 2*gp.tileSize)+gp.tileSize;

        gp.obj[1] = new OBJ_DirectionOrb();
        gp.obj[1].x = rand.nextInt(gp.screenWidth - 6*gp.tileSize)+3*gp.tileSize;
        gp.obj[1].y = rand.nextInt(gp.screenHeight - 2*gp.tileSize)+gp.tileSize;
//
//        gp.obj[2] = new OBJ_Key();
//        gp.obj[2].x = 38 * gp.tileSize;
//        gp.obj[2].worldY = 8 * gp.tileSize;
//
//        gp.obj[3] = new OBJ_Door();
//        gp.obj[3].x = 10 * gp.tileSize;
//        gp.obj[3].worldY = 11 * gp.tileSize;
//
//        gp.obj[4] = new OBJ_Door();
//        gp.obj[4].x = 8 * gp.tileSize;
//        gp.obj[4].worldY = 26 * gp.tileSize;
//
//        gp.obj[5] = new OBJ_Door();
//        gp.obj[5].x = 12 * gp.tileSize;
//        gp.obj[5].worldY = 22 * gp.tileSize;
//
//        gp.obj[6] = new OBJ_Chest();
//        gp.obj[6].x = 10 * gp.tileSize;
//        gp.obj[6].worldY = 7 * gp.tileSize;
    }
}
