package logic;

import entities.Entity;

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
        String collisionDirection = " ";

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

        int defaultPlayerX = gp.player.solidArea.x;
        int defaultPlayerY = gp.player.solidArea.y;

        int defaultPlayer2X = gp.player2.solidArea.x;
        int defaultPlayer2Y = gp.player2.solidArea.y;

        int defaultBallX = entity.solidArea.x;
        int defaultBallY = entity.solidArea.y;

        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;

        gp.player2.solidArea.x = gp.player2.x + gp.player2.solidArea.x;
        gp.player2.solidArea.y = gp.player2.y + gp.player2.solidArea.y;

        entity.solidArea.y += entity.speedY;
        entity.solidArea.x += entity.speedX;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            index = 1;
        }
        if(entity.solidArea.intersects(gp.player2.solidArea)){
            index = 2;
        }

        gp.player.solidArea.x = defaultPlayerX;
        gp.player.solidArea.y = defaultPlayerY;

        gp.player2.solidArea.x = defaultPlayer2X;
        gp.player2.solidArea.y = defaultPlayer2Y;

        entity.solidArea.x = defaultBallX;
        entity.solidArea.y = defaultBallY;


    return index;
    }
}
