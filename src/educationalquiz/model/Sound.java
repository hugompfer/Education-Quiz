/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author hugob
 */
public class Sound implements Serializable {

    private String name ;
    private Media sound;
    private MediaPlayer player;

    public Sound(String name) {
        this.name = name;
        this.sound = new Media(getClass().getResource(name).toString());
        this.player = new MediaPlayer(sound);
    }

    public void play() {
        player.play();
    }

    public void stop() {
        player.stop();
    }

    public void setVolume(double value) {
        player.setVolume(value);
    }

    public double getVolume() {
        return player.getVolume();
    }

    public void recreate() {
        this.sound = new Media(getClass().getResource(name).toString());
        this.player = new MediaPlayer(sound);
    }
}
