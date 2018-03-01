/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import educationalquiz.model.Quiz;
import educationalquiz.presenter.QuizViewPresenter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

    private VBox imageArea;
    private GridPane informationQuestion;
    private VBox questionArea;
    private HBox questionTitleArea;
    private List<Button> answers;
    private ImageView image;
    private Text questionTitle;
    private ImageView next;
    private ImageView prev;
    private HashMap<Button, Answer> btnAnswersCorrespondence;
    private Button btnBack;
    private HBox bottom;

    public QuizView(Quiz q) {
        informationQuestion = new GridPane();
        questionArea = new VBox();
        questionTitleArea = new HBox();
        imageArea = new VBox();
        answers = new ArrayList<>();
        questionTitle = new Text();
        btnAnswersCorrespondence = new HashMap<>();
        bottom = new HBox();
        inicializeButtons(q);
        setupLayout(q);
    }

    private void setupLayout(Quiz q) {
        setupTop(q.getAtualQuestion());
        setupCenter();
        setTop(questionTitleArea);
        setCenter(questionArea);
        setupBottom();
    }
    
    private void setupTop(Question question) {
        next = new ImageView(new Image("/resources/next.png"));
        next.setFitWidth(50);
        next.setFitHeight(50);

        prev = new ImageView(new Image("/resources/prev.png"));
        prev.setFitWidth(50);
        prev.setFitHeight(50);
        String s=question.getImageURL();
        image = new ImageView(new Image(s));
        image.setFitWidth(250);
        image.setFitHeight(250);
        imageArea.getChildren().add(image);
        imageArea.setAlignment(Pos.CENTER);
        
        questionTitle.setText(question.getTitle());
        questionTitle.setFont(Font.font(20));
        questionTitle.setTextAlignment(TextAlignment.CENTER);
        questionTitle.setWrappingWidth(570);
        questionTitleArea.setPadding(new Insets(10, 10, 20, 10));
        questionTitleArea.getChildren().addAll(prev, questionTitle, next);
        questionTitleArea.setAlignment(Pos.CENTER);

        questionArea.getChildren().addAll(imageArea, informationQuestion);
    }
    
    private void setupCenter() {
        informationQuestion.setPadding(new Insets(25, 10, 10, 10));
        informationQuestion.setVgap(10);
        informationQuestion.setHgap(10);
        informationQuestion.add(answers.get(0), 1, 1);
        informationQuestion.add(answers.get(1), 3, 1);
        informationQuestion.add(answers.get(2), 1, 3);
        informationQuestion.add(answers.get(3), 3, 3);
        informationQuestion.setAlignment(Pos.CENTER);
        informationQuestion.setStyle("-fx-background-fill: black, white ;\n"
                + "-fx-background-insets: 0, 1 ;");
    }

    private void setupBottom() {
        ImageView img1 = new ImageView(new Image("/resources/back.png"));
        img1.setFitWidth(50);
        img1.setFitHeight(50);
        btnBack = new Button("", img1);
        bottom.getChildren().add(btnBack);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(0, 0, 10, 0));
        setBottom(bottom);
    }

    private void incializeCorrespondence(Question question) {
        btnAnswersCorrespondence.clear();
        int i = 0;
        for (Answer answer : question.getAnswers()) {
            btnAnswersCorrespondence.put(answers.get(i++), answer);
        }

    }

    private void inicializeButtons(Quiz q) {
        for (String answer : q.getAtualQuestion().getAnswersInformation()) {
            Button btn = new Button(answer);
            btn.setMinWidth(300);
            btn.setMinHeight(100);
            btn.setMaxWidth(300);
            btn.setMaxHeight(100);
            btn.setStyle("-fx-font-size: 15");
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

        image = ImageViewBuilder.create()
                .image(new Image(question.getImageURL()))
                .build();
        questionTitle.setText(question.getTitle());

        incializeCorrespondence(question);
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
        
        btnBack.setOnAction(e->{
            presenter.back();
        });
    }

    public void paintWrongButton(Answer answer) {
        for (Map.Entry<Button, Answer> entry : btnAnswersCorrespondence.entrySet()) {
            if (entry.getValue() == answer) {
                entry.getKey().setStyle("-fx-background-color: red;");
            }
        }
    }

    public void paintCorrectButton(Answer answer) {
        for (Map.Entry<Button, Answer> entry : btnAnswersCorrespondence.entrySet()) {
            if (entry.getValue() == answer) {
                entry.getKey().setStyle("-fx-background-color: green;");
            }
        }
    }

}
