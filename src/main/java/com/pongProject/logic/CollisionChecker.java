package com.pongProject.logic;

import com.pongProject.entities.Entity;
import com.pongProject.gameFolder.Menu;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }


    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speedY) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speedY) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;


                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - (int)entity.speedX) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;


                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + (int)entity.speedX) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;

                }
                break;
        }


    }

    public int checkObject(Entity entity) {

        int index = 999;


        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;

        gp.player2.solidArea.x = gp.player2.x + gp.player2.solidArea.x;
        gp.player2.solidArea.y = gp.player2.y + gp.player2.solidArea.y;

        gp.compAI.solidArea.x = gp.compAI.x + gp.compAI.solidArea.x;
        gp.compAI.solidArea.y = gp.compAI.y + gp.compAI.solidArea.y;

        entity.solidArea.y += entity.speedY;
        entity.solidArea.x += entity.speedX;

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            index = 901;
        }

        if(Menu.playerOrComp == 1){
            if(entity.solidArea.intersects(gp.player2.solidArea)){
                index = 902;
            }
        }else if(Menu.playerOrComp==2){
            if(entity.solidArea.intersects(gp.compAI.solidArea)){
                index = 902;
            }
        }

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;
                if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                    index = i;
                }
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }

        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        gp.player2.solidArea.x = gp.player2.solidAreaDefaultX;
        gp.player2.solidArea.y = gp.player2.solidAreaDefaultY;

        gp.compAI.solidArea.x = gp.compAI.solidAreaDefaultX;
        gp.compAI.solidArea.y = gp.compAI.solidAreaDefaultY;

        entity.solidArea.x = gp.ball.solidAreaDefaultX;
        entity.solidArea.y = gp.ball.solidAreaDefaultY;

    return index;
    }
}
