package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_DirectionOrb2 extends SuperObject {
    public OBJ_DirectionOrb2() {
        name = "DirectionOrb2";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/directionOrb2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
