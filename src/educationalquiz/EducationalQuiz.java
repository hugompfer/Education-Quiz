/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz;


import educationalquiz.model.QuizManager;
import educationalquiz.view.InicialView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class EducationalQuiz extends Application {

    @Override
    public void start(Stage primaryStage) {

        
        InicialView iw = new InicialView();
        Scene scene = new Scene(iw, 700, 650);
        primaryStage.getIcons().add(new Image("resources/logo.png"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Educational Quiz!");
        primaryStage.setScene(scene);
        primaryStage.show();   

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
