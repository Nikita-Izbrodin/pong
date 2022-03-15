package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_ControlOrb  extends SuperObject {
    public OBJ_ControlOrb(){
        name = "ControlOrb";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/controlOrb.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
