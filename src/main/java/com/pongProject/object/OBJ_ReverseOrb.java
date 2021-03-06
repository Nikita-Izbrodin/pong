package com.pongProject.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_ReverseOrb extends SuperObject {
    public OBJ_ReverseOrb() {
        name = "ReverseOrb";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/pongProject/objects/reverseOrb.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}