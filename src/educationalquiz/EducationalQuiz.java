/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz;

import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import educationalquiz.model.Quiz;
import educationalquiz.model.QuizManager;
import educationalquiz.view.InicialView;
import educationalquiz.view.QuizView;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class EducationalQuiz extends Application {

    @Override
    public void start(Stage primaryStage) {

        QuizManager qm = new QuizManager();
        /*qm.addQuiz(new Quiz("fdsfs", "dffsdfsd"));
        qm.addQuiz(new Quiz("rewrwe", "rew"));
        qm.addQuiz(new Quiz("fdsfs", "dffsgdfgddfsd"));
        qm.addQuiz(new Quiz("dvxcv", "fsad"));
        qm.addQuiz(new Quiz("vx", "dgf"));
        qm.addQuiz(new Quiz("xvxxcvx", "dg"));*/

        InicialView iw = new InicialView(qm);
        Scene scene = new Scene(iw, 700, 650);

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
