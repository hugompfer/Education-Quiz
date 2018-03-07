/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import educationalquiz.model.Quiz;
import educationalquiz.model.QuizGameplay;
import educationalquiz.model.Sound;
import educationalquiz.presenter.QuizViewPresenter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author hugob
 */
public class QuizView extends BorderPane {

    private List<Button> answers;
    private Text questionTitle;
    private ImageView next;
    private ImageView prev;
    private HashMap<Button, Answer> btnAnswersCorrespondence;
    private Button btnBack;
    private HBox bottom;
    private ImageView image;
;

    public QuizView(QuizGameplay q) {
        answers = new ArrayList<>();
        btnAnswersCorrespondence = new HashMap<>();
        bottom = new HBox();
        questionTitle = new Text();
        btnBack = new Button();
        inicializeButtons(q);
        setupLayout(q);
    }

    private void setupStyles() {
        getStylesheets().add("css/Quiz.css");
        setId("root");
        btnBack.setId("btnBack");
        questionTitle.setId("questionTitle");
    }

    private void setupLayout(QuizGameplay q) {
        setupTop(q);
        setCenter(setupCenter(q.getAtualQuestion()));
        setupStyles();
    }

    private void setupTop(QuizGameplay quiz) {
        Text text = new Text(quiz.getQuizCategory()+ " - " + quiz.getQuizName());
        text.setId("title");
        HBox hbox = new HBox();
        hbox.setId("topBox");
        hbox.getChildren().addAll(btnBack, text);

        HBox title = setupQuestionTitle(quiz.getAtualQuestion());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hbox, title);
        setTop(vbox);
    }

    private HBox setupQuestionTitle(Question question) {
        next = new ImageView(new Image("/resources/next.png"));
        next.setFitWidth(50);
        next.setFitHeight(50);

        prev = new ImageView(new Image("/resources/prev.png"));
        prev.setFitWidth(50);
        prev.setFitHeight(50);

        questionTitle = new Text();
        questionTitle.setText(question.getTitle());
        questionTitle.setWrappingWidth(570);

        HBox questionTitleArea = new HBox();
        questionTitleArea.setId("questionArea");
        questionTitleArea.getChildren().addAll(prev, questionTitle, next);

        return questionTitleArea;

    }

    private VBox setupImageArea(String s) {
        VBox imageArea = new VBox();
        image = new ImageView(new Image(getClass().getResource(s).toExternalForm()));
        image.setFitWidth(200);
        image.setFitHeight(200);
        image.setPreserveRatio(true);
        imageArea.setAlignment(Pos.CENTER);
        imageArea.getChildren().add(image);
        return imageArea;
    }

    private VBox setupCenter(Question question) {
        GridPane informationQuestion = new GridPane();
        informationQuestion.setId("gridPane");
        informationQuestion.add(answers.get(0), 1, 1);
        informationQuestion.add(answers.get(1), 3, 1);
        informationQuestion.add(answers.get(2), 1, 3);
        informationQuestion.add(answers.get(3), 3, 3);
        informationQuestion.setAlignment(Pos.CENTER);

        VBox questionArea = new VBox();
        String s = question.getImageURL();
        if (s != null) {
            questionArea.getChildren().addAll(setupImageArea(s), informationQuestion);

        } else {
            questionArea.getChildren().addAll(informationQuestion);
            questionArea.setPadding(new Insets(60, 0, 0, 0));
        }
        return questionArea;
    }

    private void incializeCorrespondence(Question question) {
        btnAnswersCorrespondence.clear();
        int i = 0;
        for (Answer answer : question.getAnswers()) {
            btnAnswersCorrespondence.put(answers.get(i++), answer);
        }

    }

    private void inicializeButtons(QuizGameplay q) {
        for (String answer : q.getAtualQuestion().getAnswersInformation()) {
            Button btn = new Button(answer);
            btn.wrapTextProperty().setValue(true);
            answers.add(btn);
        }
        incializeCorrespondence(q.getAtualQuestion());
    }

    public void setupQuestionLayout(Question question) {
        resetButtons();
        int index = 0;
        for (String answer : question.getAnswersInformation()) {
            answers.get(index++).setText(answer);
        }
       
        questionTitle.setText(question.getTitle());
        incializeCorrespondence(question);
        setCenter(setupCenter(question));
    }

    private void resetButtons() {
        for (Button btn : answers) {
            btn.setStyle(null);
        }
    }

    public void setTriggers(QuizViewPresenter presenter) {
        prev.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                presenter.previousQuestion();
            }
        });

        next.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                presenter.nextQuestion();
            }
        });

        for (Button btn : answers) {
            btn.setOnAction(e -> {
                presenter.checkAnswer(btnAnswersCorrespondence.get(btn));
            });
        }

        btnBack.setOnAction(e -> {
            presenter.back();
        });
    }

    public void paintWrongButton(Answer answer) {
        for (Map.Entry<Button, Answer> entry : btnAnswersCorrespondence.entrySet()) {
            if (entry.getValue() == answer) {
                entry.getKey().setStyle("-fx-background-color: #f94848;-fx-text-fill: white;");
            }
        }
    }

    public void paintCorrectButton(Answer answer) {
        for (Map.Entry<Button, Answer> entry : btnAnswersCorrespondence.entrySet()) {
            if (entry.getValue() == answer) {
                entry.getKey().setStyle("-fx-background-color: #82d604;");
            }
        }
    }

    
    public boolean showInfo() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Terminar quiz");
        alert.setContentText("Pretende terminar o quiz?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
