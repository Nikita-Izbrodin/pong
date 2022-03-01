package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DirectionOrb extends SuperObject {
    public OBJ_DirectionOrb(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/key.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
