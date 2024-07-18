package gh.nijd.pong;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

public class Console {

    public FXMLLoader loadScene(String fileName, Stage stage, boolean fullscreen) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        Scene scene = new Scene(loader.load());
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.setFullScreen(fullscreen);
        stage.show();
        return loader;
    }

    public void playMusic(String audioName) { // https://www.baeldung.com/java-play-sound
        try {
            boolean clipClosed = false;
            AudioInputStream AISone = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/gh/nijd/pong/sfx/game.wav"));
            BufferedInputStream BIS = new BufferedInputStream(AISone);
            AudioInputStream AIStwo = new AudioInputStream(BIS, AISone.getFormat(), AISone.getFrameLength());
            AudioFormat AF = AIStwo.getFormat();
            DataLine.Info audioInfo = new DataLine.Info(Clip.class, AF);
            Line audioLine = AudioSystem.getLine(audioInfo);
            Clip audioClip = (Clip) audioLine;
            audioClip.addLineListener(new sfxListener());
            audioClip.open(AIStwo);
            audioClip.start();
            AIStwo.close();
            AISone.close();
            BIS.close();
            while (audioClip.isActive()) {
                while (!audioClip.isRunning() && clipClosed) {
                    audioClip.close();
                    clipClosed = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}