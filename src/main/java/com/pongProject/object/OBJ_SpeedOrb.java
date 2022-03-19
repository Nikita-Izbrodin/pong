package com.pongProject.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_SpeedOrb extends SuperObject {
    public OBJ_SpeedOrb(){
        name = "SpeedOrb";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/pongProject/objects/speedOrb.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
