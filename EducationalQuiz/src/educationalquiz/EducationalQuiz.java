/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz;

import educationalquiz.view.QuizzesLibrary;
import educationalquiz.model.*;
import educationalquiz.view.AnswersViewer;
import educationalquiz.view.CreateQuestion;
import educationalquiz.view.CreateQuiz;
import educationalquiz.view.InicialView;

import educationalquiz.view.QuizView;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class EducationalQuiz extends Application {

    @Override
    public void start(Stage primaryStage) {

        QuizManager qm=new QuizManager();
        
        InicialView iw=new InicialView(qm);
        
        Scene scene = new Scene(iw, 700, 700);

        primaryStage.setTitle("Hello World!");
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
