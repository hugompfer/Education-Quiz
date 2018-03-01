/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author hugob
 */
public class MenuButton extends Button{
    
    /**
     * MenuButton criar um novo botão
     *
     */
    public MenuButton(){
        super();
        setStyle("-fx-background-color:#E6EFF3;"
                + "-fx-border: 12px solid;"
                + " -fx-border-color: black;"
                + " -fx-background-radius: 5.0;"
                + " -fx-border-radius: 5.0");
    }
    
    /**
     * MenuButton criar um novo botão com um texto e uma image view
     *
     * @param text texto pretendido
     * @param iv image view pretendida
     */
    public MenuButton(String text,ImageView iv){
        super(text,iv);
        setStyle("-fx-background-color:#E6EFF3;"
                + "-fx-border: 12px solid;"
                + " -fx-border-color: black;"
                + " -fx-background-radius: 5.0;"
                + " -fx-border-radius: 5.0");
    }
}
