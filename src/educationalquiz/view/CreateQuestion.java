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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 *
 * @author hugob
 */
public class CreateQuestion extends BorderPane {

    private VBox imageArea;
    private ImageView image;
    private TextArea title;
    private URI source;
    private ArrayList<TextArea> answers;
    private ArrayList<RadioButton> radioButtons;
    private ToggleGroup checks;
    private Button btnConfirm;
    private Button btnBack;
    private Question question;

    public CreateQuestion() {
        imageArea = new VBox();
        image = new ImageView();
        title = new TextArea();
        answers = inicializeAnswers();
        checks = new ToggleGroup();
        radioButtons = inicializeRadioButtons();
        source = null;
        setupLayout();
        setupBehaviour();
    }

    public CreateQuestion(Question question) {
        this();
        this.question = question;
        setupQuestionLayout();
    }

    public CreateQuestion(String title) {
        this();
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
        ArrayList<TextArea> textFields = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TextArea tf = new TextArea();
            tf.setId("textFieldAnswer");
            textFields.add(tf);
        }
        return textFields;
    }

    private ArrayList<RadioButton> inicializeRadioButtons() {
        ArrayList<RadioButton> rb = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            rb.add(new RadioButton());
        }
        return rb;
    }

    private void setupTop() {
        Text text = new Text("Nova Questão");
        text.setId("title");
        HBox hbox = new HBox();
        hbox.setId("topBox");
        hbox.getChildren().addAll(btnBack, text);
        setTop(hbox);
    }

    private void setupButtons() {
        btnBack = new Button();
        btnConfirm = new Button("Concluir");
    }

    private void setupStyles() {
        getStylesheets().add("css/Library.css");
        setId("root");
        btnBack.setId("btnBack");
        btnConfirm.setId("btnConfirm");
    }

    private void setupLayout() {
        setupButtons();
        setupStyles();
        setupTop();
        HBox firstZone = inicializeFirstZone();
        HBox secondZone = inicializeSecondZone();
        VBox center = new VBox();
        center.setId("centerBoxsSeparation");
        center.getChildren().addAll(firstZone, secondZone);
        setCenter(center);
        inicializeBottomButtons();
    }

    private VBox createImage() {
        VBox vbox = new VBox();
        image = incializeImageView("/resources/interrogation.png");
        imageArea.getChildren().add(image);
        imageArea.setId("imageArea");
        vbox.setId("separaton");
        vbox.getChildren().addAll(new Label("Imagem"), imageArea);
        return vbox;
    }

    private VBox createQuestionZone() {
        VBox vbox = new VBox();
        vbox.setId("separaton");
        vbox.getChildren().addAll(new Label("Pergunta"), title);
        return vbox;
    }

    private void inicializeBottomButtons() {
        HBox bottom = new HBox();
        bottom.setId("bottomCenter");
        bottom.getChildren().add(btnConfirm);
        setBottom(bottom);
    }

    private HBox inicializeFirstZone() {
        HBox hbox = new HBox();
        hbox.setId("zone");
        hbox.getChildren().addAll(createImage(), createQuestionZone());
        return hbox;
    }

    private HBox inicializeSecondZone() {
        HBox hbox = new HBox();
        hbox.setId("zone");
        hbox.getChildren().addAll(createAnswers(), createRadioButtons());
        return hbox;
    }

    private VBox createAnswers() {
        VBox vbox = new VBox();
        vbox.setId("separaton");
        vbox.getChildren().addAll(new Label("Resposta 1 "), answers.get(0), new Label("Resposta 2 "), answers.get(1),
                new Label("Resposta 3 "), answers.get(2), new Label("Resposta 4 "), answers.get(3));
        return vbox;
    }

    private VBox createRadioButtons() {
        VBox vbox = new VBox();
        RadioButton boxAnswer1 = radioButtons.get(0);
        RadioButton boxAnswer2 = radioButtons.get(1);
        RadioButton boxAnswer3 = radioButtons.get(2);
        RadioButton boxAnswer4 = radioButtons.get(3);
        boxAnswer1.setToggleGroup(checks);
        boxAnswer2.setToggleGroup(checks);
        boxAnswer3.setToggleGroup(checks);
        boxAnswer4.setToggleGroup(checks);
        VBox answersBoxs = new VBox();
        answersBoxs.setId("radioButtons");
        answersBoxs.getChildren().addAll(boxAnswer1, boxAnswer2, boxAnswer3, boxAnswer4);
        vbox.getChildren().addAll(new Label("Correta "), answersBoxs);
        return vbox;
    }

    private ImageView incializeImageView(String url) {
        ImageView img = new ImageView(new Image(url));
        img.setFitWidth(145);
        img.setFitHeight(145);
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
        btnBack.setOnAction(e -> {
            presenter.back();
        });

        btnConfirm.setOnMouseClicked(e -> {
            presenter.createQuestion(source, createAswers(), title.getText());
        });

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
        question.setImageURL(source !=null ? source.toString():"resources/interrogation.png");
    }

    public void setTriggers(EditQuestionPresenter presenter) {
        btnBack.setOnAction(e -> {
            presenter.back();
        });

        btnConfirm.setOnMouseClicked(e -> {
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
