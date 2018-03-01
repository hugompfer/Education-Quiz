/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import educationalquiz.model.QuizManager;
import educationalquiz.presenter.CreateQuestionPresenter;
import educationalquiz.presenter.EditQuestionPresenter;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 *
 * @author hugob
 */
public class CreateQuestion extends VBox {

    private GridPane questionInformation;
    private VBox imageArea;
    private ImageView image;
    private VBox top;
    private TextArea title;
    private URI source;
    private ArrayList<TextArea> answers;
    private ArrayList<RadioButton> radioButtons;
    private ToggleGroup checks;
    private List<Button> btnAnswer;
    private Button confirm;
    private Button cancel;
    private HBox bottom;
    private QuizManager manager;
    private Question question;

    public CreateQuestion(QuizManager manager) {
        imageArea = new VBox();
        image = new ImageView();
        top = new VBox();
        title = new TextArea();
        answers = inicializeAnswers();
        questionInformation = new GridPane();
        checks = new ToggleGroup();
        radioButtons = inicializeRadioButtons();
        btnAnswer = new ArrayList<>();
        bottom = new HBox();
        source = null;
        this.manager = manager;
        setupLayout();
        setupBehaviour();
    }

    public CreateQuestion(Question question, QuizManager manager) {
        this(manager);
        this.question = question;
        setupQuestionLayout();
    }

    public CreateQuestion(String title, QuizManager manager) {
        this(manager);
        this.title.setText(title);
    }

    private void setupQuestionLayout() {
        title.setText(question.getTitle());
        int i = 0;
        ArrayList<Answer> answersFromQuestion = (ArrayList<Answer>) question.getAnswers();
        for (TextArea ta : answers) {
            Answer a = answersFromQuestion.get(i);
            if (a != null) {
                ta.setText(a.getInformation());
                radioButtons.get(i).setSelected(a.isCorrect());
            }
            i++;
        }
        String str = question.getImageURL();
        image = incializeImageView(str != null ? str : "/resources/interrogation.png");
        imageArea.getChildren().clear();
        imageArea.getChildren().add(image);
    }

    private ArrayList<TextArea> inicializeAnswers() {
        ArrayList<TextArea> textAreas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            textAreas.add(new TextArea());
        }
        return textAreas;
    }

    private ArrayList<RadioButton> inicializeRadioButtons() {
        ArrayList<RadioButton> rb = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            rb.add(new RadioButton());
        }
        return rb;
    }

    private void setupLayout() {
        inicializeInformation();
        incializeTextArea();
        inicializeRadioButton();
        inicializeButtons();
        inicializeBottomButtons();
        image = incializeImageView("/resources/interrogation.png");
        imageArea.getChildren().add(image);
        imageArea.setStyle("-fx-border-color: black;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n");
        imageArea.setMinSize(200, 200);
        imageArea.setMaxSize(200, 200);
        imageArea.setTranslateX(240);
        imageArea.setTranslateY(10);
        imageArea.setAlignment(Pos.CENTER);

        Text intro = new Text("Criação de uma nova questão");
        intro.setFont(new Font(25));
        intro.setTranslateX(177);

        top.setPadding(new Insets(10, 15, 10, 15));
        top.getChildren().addAll(intro, imageArea, questionInformation);

        getChildren().addAll(top, bottom);
    }

    private void inicializeBottomButtons() {
        ImageView img1 = new ImageView(new Image("/resources/cancel.png"));
        img1.setFitWidth(50);
        img1.setFitHeight(50);
        ImageView img2 = new ImageView(new Image("/resources/accept.png"));
        img2.setFitWidth(50);
        img2.setFitHeight(50);
        cancel = new MenuButton("", img1);
        confirm = new MenuButton("", img2);

        confirm.setMaxSize(50, 50);
        confirm.setMinSize(50, 50);
        cancel.setMaxSize(50, 50);
        cancel.setMinSize(50, 50);
        bottom.getChildren().addAll(confirm, cancel);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(20);
    }

    private void inicializeInformation() {
        Label lblTitle = new Label("Pergunta: ");
        Label lblAnswer1 = new Label("Resposta 1: ");
        Label lblAnswer2 = new Label("Resposta 2: ");
        Label lblAnswer3 = new Label("Resposta 3: ");
        Label lblAnswer4 = new Label("Resposta 4: ");

        questionInformation.setVgap(10);
        questionInformation.setHgap(10);
        questionInformation.add(lblTitle, 5, 3);
        questionInformation.add(lblAnswer1, 5, 7);
        questionInformation.add(lblAnswer2, 5, 10);
        questionInformation.add(lblAnswer3, 5, 13);
        questionInformation.add(lblAnswer4, 5, 16);
        questionInformation.add(title, 10, 3);
        questionInformation.add(answers.get(0), 10, 7);
        questionInformation.add(answers.get(1), 10, 10);
        questionInformation.add(answers.get(2), 10, 13);
        questionInformation.add(answers.get(3), 10, 16);
    }

    private void inicializeRadioButton() {
        RadioButton boxAnswer1 = radioButtons.get(0);
        RadioButton boxAnswer2 = radioButtons.get(1);
        RadioButton boxAnswer3 = radioButtons.get(2);
        RadioButton boxAnswer4 = radioButtons.get(3);
        boxAnswer1.setToggleGroup(checks);
        boxAnswer1.setAlignment(Pos.BOTTOM_RIGHT);
        boxAnswer2.setToggleGroup(checks);
        boxAnswer3.setToggleGroup(checks);
        boxAnswer4.setToggleGroup(checks);
        questionInformation.add(new Label("Correta"), 12, 5);
        questionInformation.add(new Label("Respostas"), 13, 5);
        questionInformation.add(boxAnswer1, 12, 7);
        questionInformation.add(boxAnswer2, 12, 10);
        questionInformation.add(boxAnswer3, 12, 13);
        questionInformation.add(boxAnswer4, 12, 16);
        GridPane.setHalignment(boxAnswer1, HPos.CENTER);
        GridPane.setHalignment(boxAnswer2, HPos.CENTER);
        GridPane.setHalignment(boxAnswer3, HPos.CENTER);
        GridPane.setHalignment(boxAnswer4, HPos.CENTER);

    }

    private void incializeTextArea() {
        title.setMaxSize(300, 45);
        title.setMinSize(300, 45);
        TextArea answer1 = answers.get(0);
        TextArea answer2 = answers.get(1);
        TextArea answer3 = answers.get(2);
        TextArea answer4 = answers.get(3);
        answer1.setMaxSize(300, 40);
        answer1.setMinSize(300, 40);
        answer2.setMaxSize(300, 40);
        answer2.setMinSize(300, 40);
        answer3.setMaxSize(300, 40);
        answer3.setMinSize(300, 40);
        answer4.setMaxSize(300, 40);
        answer4.setMinSize(300, 40);
    }

    private void inicializeButtons() {
        for (int i = 0; i < 4; i++) {
            Button btn = new Button("...");
            btn.setMaxSize(40, 40);
            btn.setMinSize(20, 20);
            btnAnswer.add(btn);
        }

        questionInformation.add(btnAnswer.get(0), 13, 7);
        questionInformation.add(btnAnswer.get(1), 13, 10);
        questionInformation.add(btnAnswer.get(2), 13, 13);
        questionInformation.add(btnAnswer.get(3), 13, 16);
        GridPane.setHalignment(btnAnswer.get(0), HPos.CENTER);
        GridPane.setHalignment(btnAnswer.get(1), HPos.CENTER);
        GridPane.setHalignment(btnAnswer.get(2), HPos.CENTER);
        GridPane.setHalignment(btnAnswer.get(3), HPos.CENTER);
    }

    private ImageView incializeImageView(String url) {
        ImageView img = new ImageView(new Image(url));
        img.setFitWidth(200);
        img.setFitHeight(200);
        return img;
    }

    private void setupBehaviour() {
        imageArea.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Escolha uma imagem");
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                URI url = file.toURI();
                if (url != null && !url.toString().isEmpty()) {
                    imageArea.getChildren().remove(image);
                    image = incializeImageView(url.toString());
                    imageArea.getChildren().add(image);
                    this.source = url;
                }
            }
        });
    }

    public void setTriggers(CreateQuestionPresenter presenter) {
        cancel.setOnAction(e -> {
            presenter.back();
        });

        confirm.setOnMouseClicked(e -> {
            presenter.createQuestion(source, createAswers(), title.getText());
        });

        for (Button btn : btnAnswer) {
            btn.setOnAction(e -> {
                updateQuestion();
                presenter.reuseQuestion(question);
            });
        }

    }

    private Answer[] createAswers() {
        Answer[] answers = new Answer[4];
        answers[0] = new Answer(this.answers.get(0).getText(), this.radioButtons.get(0).isSelected());
        answers[1] = new Answer(this.answers.get(1).getText(), this.radioButtons.get(1).isSelected());
        answers[2] = new Answer(this.answers.get(2).getText(), this.radioButtons.get(2).isSelected());
        answers[3] = new Answer(this.answers.get(3).getText(), this.radioButtons.get(3).isSelected());
        return answers;
    }

    private void updateQuestion() {
        question.setTitle(title.getText());
        question.addAnswers(createAswers());
        question.setImageURL(source.toString());
    }

    public void setTriggers(EditQuestionPresenter presenter) {
        cancel.setOnAction(e -> {
            presenter.back();
        });

        confirm.setOnMouseClicked(e -> {
            presenter.editQuestion(source, createAswers(), title.getText());
        });

    }

    public void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Verificação");
        alert.setHeaderText("Criaçao de uma questão");
        alert.setContentText(info);
        alert.showAndWait();
    }

}
