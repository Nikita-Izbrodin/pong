package tile;

import gameFolder.Menu;
import logic.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    public int mapCounter;
    public int mapNum;

    public TileManager(GamePanel gp){

        if (Menu.globalDifficulty == 2) {
            gp.maxScreenCol = 22;
            gp.maxScreenRow = 18;
        }
        mapCounter = 1;
        mapNum = 1;
        this.gp = gp;
        tile = new Tile[15];
        mapTileNum = new int[(gp.maxScreenCol + 2)][(gp.maxScreenRow + 2)];
        getTileImage();

        if(Menu.globalDifficulty ==1){
            loadMap("/resources/maps/map02.txt");
        }
        else if (Menu.globalDifficulty ==2){
            loadMap("/resources/maps/map01.txt");
        }
    }


    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/default.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleL.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleR.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/default2.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleL2.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/middleR2.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/red.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/blue.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/green.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wallPurple.png"));
            tile[10].collision = true;


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0,row=0;

            while(col < gp.maxScreenCol && row< gp.maxScreenRow){

                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }

        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){
        int col = 0, row = 0, x = 0, y = 0;

        if (Menu.discoMode && Menu.globalDifficulty == 1){
            if (mapCounter > 12) {
                if (mapNum == 1) {
                    mapNum = 2;
                    loadMap("/resources/maps/mapR2.txt");
                } else if (mapNum == 2) {
                    mapNum = 3;
                    loadMap("/resources/maps/mapR3.txt");
                }else if (mapNum == 3) {
                    mapNum = 1;
                    loadMap("/resources/maps/mapR1.txt");
                }
                mapCounter = 0;
            }
        }
        if(Menu.discoMode && Menu.globalDifficulty == 2){
            if(mapCounter > 12){
                if (mapNum == 1) {
                    mapNum = 2;
                    loadMap("/resources/maps/mapHR2.txt");
                } else if (mapNum == 2) {
                    mapNum = 3;
                    loadMap("/resources/maps/mapHR3.txt");
                }else if (mapNum == 3) {
                    mapNum = 1;
                    loadMap("/resources/maps/mapHR1.txt");
                }
                mapCounter = 0;
            }
        }

        mapCounter++;

        while(col < (gp.maxScreenCol) && row< (gp.maxScreenRow)){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == (gp.maxScreenCol)){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }


    }
}
