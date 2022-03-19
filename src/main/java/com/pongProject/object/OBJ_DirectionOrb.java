package com.pongProject.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_DirectionOrb extends SuperObject {
    public OBJ_DirectionOrb(){
        name = "DirectionOrb";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/pongProject/objects/directionOrb.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
