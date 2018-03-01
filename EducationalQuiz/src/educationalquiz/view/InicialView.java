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

    private HBox top;
    private VBox center;
    private Button btnLibrary;
    private Button btnNewQuiz;
    private QuizManager manager;
    
    public InicialView(QuizManager manager) {
        top = new HBox();
        center = new VBox();
        btnLibrary = new Button("Meus Quizes");
        btnNewQuiz = new Button("Novo Quiz");
        this.manager=manager;
        setupLayout();
        setupBehaviour();
    }

    private void setupLayout() {
        inicializeTop();
        inicializeCenter();
        getChildren().addAll(top, center);
    }

    private void inicializeTop() {
        ImageView img = new ImageView(new Image("/resources/interrogation.png"));
        img.setFitWidth(150);
        img.setFitHeight(150);
        top.getChildren().add(img);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(20, 0, 20, 0));
    }

    private void inicializeCenter() {
        btnNewQuiz.setStyle("-fx-font-size: 15");
        btnLibrary.setStyle("-fx-font-size: 15");
        btnLibrary.setMinWidth(300);
        btnLibrary.setMinHeight(100);
        btnLibrary.setMaxWidth(300);
        btnLibrary.setMaxHeight(100);
        btnNewQuiz.setMinWidth(300);
        btnNewQuiz.setMinHeight(100);
        btnNewQuiz.setMaxWidth(300);
        btnNewQuiz.setMaxHeight(100);
        center.getChildren().addAll(btnLibrary, btnNewQuiz);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(20, 0, 20, 0));
        center.setSpacing(50);
    }

    private void setupBehaviour() {
        btnLibrary.setOnAction(e->{
            
            QuizzesLibrary view = new QuizzesLibrary(manager);
            QuizzesLibraryPresenter p = new QuizzesLibraryPresenter(manager, view);
            Stage stage = (Stage) getScene().getWindow();
            stage.setScene(new Scene(view, 700, 650));
        }
        );
        
        btnNewQuiz.setOnAction(e->{
        
            CreateQuiz view=new CreateQuiz(manager);
            CreateQuizPresenter p=new CreateQuizPresenter(manager, view,new Quiz());
            Stage stage = (Stage) getScene().getWindow();
            stage.setScene(new Scene(view, 700, 700));
        }
        );
    }
}
