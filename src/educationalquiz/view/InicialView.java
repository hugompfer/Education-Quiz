/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Quiz;
import educationalquiz.model.QuizManager;
import educationalquiz.presenter.CreateQuizPresenter;
import educationalquiz.presenter.QuizzesLibraryPresenter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class InicialView extends VBox {

    private Button btnLibrary;
    private Button btnNewQuiz;
    private QuizManager manager;

    public InicialView(QuizManager manager) {

        btnLibrary = new Button("MEUS QUIZES");
        btnNewQuiz = new Button("NOVO QUIZ");
        this.manager = manager;
        setupLayout();
        setupBehaviour();
        setupStyles();
    }

    private void setupStyles() {
        getStylesheets().add("css/InicialView.css");
        setId("root");
        btnLibrary.setId("");
    }

    private void setupLayout() {

        VBox center = new VBox();
        center.getChildren().addAll(btnNewQuiz,btnLibrary);
        center.setId("center");
        getChildren().addAll(inicializeTop(), center);
    }

    private HBox inicializeTop() {
        ImageView img = new ImageView(new Image("/resources/interrogation.png"));
        img.setFitWidth(150);
        img.setFitHeight(150);
        HBox top = new HBox();
        top.getChildren().add(img);
        top.setId("top");
        return top;
    }

    private void setupBehaviour() {
        btnLibrary.setOnAction(e -> {
            QuizzesLibrary view = new QuizzesLibrary(manager.getQuizzes());
            QuizzesLibraryPresenter p = new QuizzesLibraryPresenter(manager, view);
            Stage stage = (Stage) getScene().getWindow();
            stage.setScene(new Scene(view, 700, 650));
        }
        );

        btnNewQuiz.setOnAction(e -> {
            CreateQuiz view = new CreateQuiz();
            CreateQuizPresenter p = new CreateQuizPresenter(manager, view, new Quiz());
            Stage stage = (Stage) getScene().getWindow();
            stage.setScene(new Scene(view, 700, 700));
        }
        );
    }
}
