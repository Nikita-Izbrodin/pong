package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_SpeedOrb extends SuperObject {
    public OBJ_SpeedOrb(){
        name = "SpeedOrb";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/speedOrb.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
