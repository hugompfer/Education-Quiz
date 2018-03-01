/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import com.sun.javafx.scene.control.skin.ComboBoxPopupControl;
import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import educationalquiz.presenter.CreateQuizPresenter;
import educationalquiz.presenter.ListViewerPresenter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author hugob
 */
public class ListViewer extends VBox {

    private HBox top;
    private VBox center;
    private HBox bottom;
    private ListView<Question> listQuestion;
    private ListView<Answer> listAnswer;
    private TextField filter;
    private Button back;
    private boolean questions;
    private Question question;

    public ListViewer(Set<Question> listQuestions, Question question) {
        top = new HBox();
        center = new VBox();
        bottom = new HBox();
        listQuestion = new ListView<>();
        listQuestion.setItems(FXCollections.observableArrayList((new ArrayList<>(listQuestions))));
        listAnswer = null;
        questions = true;
        filter = new TextField();
        this.question = question;
        setupLayout();
    }

    public ListViewer(HashSet<Answer> listAnswers, Question question) {
        top = new HBox();
        center = new VBox();
        bottom = new HBox();
        listAnswer = new ListView<>();
        listAnswer.setItems(FXCollections.observableArrayList((new ArrayList<>(listAnswers))));
        listQuestion = null;
        filter = new TextField();
        questions = false;
        this.question = question;
        setupLayout();
    }

    private void setupLayout() {
        inicializeTop();
        inicializeCenter();
        inicializeBottom();

        getChildren().addAll(top, center, bottom);
    }

    private void inicializeTop() {
        Text text = new Text(questions ? "Lista de Questões" : "Lista de Respostas");
        text.setFont(new Font(20));
        top.getChildren().add(text);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10, 0, 0, 0));
    }

    private void inicializeCenter() {
        Label lblFilter = new Label(questions ? "Pesquise a pergunta desejada:" : "Pesquise a resposta desejada:");
        HBox box = new HBox();
        filter.setMinSize(300, 25);
        filter.setMaxSize(300, 25);

        box.getChildren().addAll(lblFilter, filter);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(0, 0, 10, 0));
        box.setSpacing(90);

        center.getChildren().add(box);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(20, 0, 20, 0));

        if (questions) {
            listQuestion.setMaxSize(550, 350);
            listQuestion.setMinSize(550, 350);
            center.getChildren().add(listQuestion);
        } else {
            listAnswer.setMaxSize(550, 350);
            listAnswer.setMinSize(550, 350);
            center.getChildren().add(listAnswer);
        }

    }

    private void inicializeBottom() {
        ImageView img1 = new ImageView(new Image("/resources/back.png"));
        img1.setFitWidth(50);
        img1.setFitHeight(50);
        back = new Button("", img1);
        back.setMaxSize(50, 50);
        back.setMinSize(50, 50);
        bottom.getChildren().add(back);
        bottom.setAlignment(Pos.CENTER);
    }

    public void setTriggers(ListViewerPresenter presenter) {
        back.setOnAction(e -> {
            presenter.back(questions);
        });

        if (questions) {
            listQuestion.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    presenter.enterQuestion(listQuestion.getSelectionModel().getSelectedItem().getTitle(), question);
                }
            });
        } else {
            listAnswer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    presenter.enterAnswer(listAnswer.getSelectionModel().getSelectedItem().getInformation(), question);
                }
            });
        }
    }
}
