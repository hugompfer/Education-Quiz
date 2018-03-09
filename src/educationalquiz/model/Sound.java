/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.Serializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Represents a audio file to make sound
 *
 * @author hugob
 */
public class Sound implements Serializable {

    private String name;
    private Media sound;
    private MediaPlayer player;

    /**
     * Create a sound with the music name
     *
     * @param name name of music
     */
    public Sound(String name) {
        this.name = name;
        this.sound = new Media(getClass().getResource(name).toString());
        this.player = new MediaPlayer(sound);
    }

    /**
     * Play the music
     *
     */
    public void play() {
        player.play();
    }

     /**
     * Stop the music
     *
     */
    public void stop() {
        player.stop();
    }


    public void recreate() {
        this.sound = new Media(getClass().getResource(name).toString());
        this.player = new MediaPlayer(sound);
    }
}
