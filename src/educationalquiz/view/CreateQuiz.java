/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Question;
import educationalquiz.model.Quiz;
import educationalquiz.model.QuizManager;
import educationalquiz.presenter.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author hugob
 */
public class CreateQuiz extends VBox {

    private HBox top;
    private VBox title;
    private GridPane quizInformation;
    private VBox questionsArea;
    private ListView<Question> questionsView;
    private TextField name;
    private TextField categoty;
    private Button confirm;
    private Button cancel;
    private Button more;
    private HBox bottom;
    private QuizManager manager;
    private ComboBox cbxOptions;

    public CreateQuiz(QuizManager manager) {
        top = new HBox();
        quizInformation = new GridPane();
        this.manager = manager;
        questionsArea = new VBox();
        title = new VBox();
        bottom = new HBox();
        questionsView = new ListView<>();
        name = new TextField();
        categoty = new TextField();

        setupLayout();
    }

    public CreateQuiz(QuizManager manager, Quiz quiz) {
        this(manager);
        ObservableList<Question> observableArrayList
                = FXCollections.observableArrayList(quiz.getQuestions());
        questionsView.setItems(observableArrayList);
        categoty.setText(quiz.getCategory());
        name.setText(quiz.getName());
    }

    private void setupLayout() {
        inicializeBottomButtons();
        inicializeCenter();
        Text intro = new Text("Criação de um novo Quiz");
        intro.setFont(new Font(25));

        Text questions = new Text("Questões: ");
        questions.setFont(new Font(15));
        HBox box = new HBox(questions);
        box.setPadding(new Insets(35, 0, 0, 0));
        Label lblQuestions = new Label("Pretende: ");
        lblQuestions.setTranslateX(320);

        box.getChildren().addAll(lblQuestions, cbxOptions);
        title.setPadding(new Insets(5, 10, 10, 15));
        title.getChildren().addAll(intro, quizInformation, box);
        top.setPadding(new Insets(10, 15, 10, 15));
        top.getChildren().addAll(title);
        getChildren().addAll(top, questionsArea, bottom);

    }

    private void inicializeCenter() {
        cbxOptions = new ComboBox();
        cbxOptions.setItems(FXCollections.observableArrayList(
                "Reutilizar Questões", "Criar novas questões"));
        Label lblName = new Label("Nome: ");
        Label lblCategory = new Label("Categoria: ");
        cbxOptions.setTranslateX(350);
        quizInformation.setVgap(10);
        quizInformation.setHgap(10);
        quizInformation.add(lblName, 0, 3);
        quizInformation.add(lblCategory, 0, 6);
        quizInformation.add(name, 1, 3);
        quizInformation.add(categoty, 1, 6);

        name.setMaxSize(300, 25);
        name.setMinSize(300, 25);
        categoty.setMaxSize(300, 25);
        categoty.setMinSize(300, 25);

        questionsView.setMinSize(650, 300);
        questionsView.setMaxSize(650, 300);
        questionsArea.setPadding(new Insets(10, 0, 0, 0));
        questionsArea.setAlignment(Pos.CENTER);
        questionsArea.getChildren().add(questionsView);
    }

    private void inicializeBottomButtons() {
        ImageView img1 = new ImageView(new Image("/resources/cancel.png"));
        img1.setFitWidth(50);
        img1.setFitHeight(50);
        ImageView img2 = new ImageView(new Image("/resources/accept.png"));
        img2.setFitWidth(50);
        img2.setFitHeight(50);
        ImageView img3 = new ImageView(new Image("/resources/more.png"));
        img3.setFitWidth(50);
        img3.setFitHeight(50);;
        cancel = new MenuButton("", img1);
        confirm = new MenuButton("", img2);
        more = new Button("", img3);

        confirm.setMaxSize(50, 50);
        confirm.setMinSize(50, 50);
        cancel.setMaxSize(50, 50);
        cancel.setMinSize(50, 50);
        more.setMaxSize(50, 50);
        more.setMinSize(50, 50);

        bottom.getChildren().addAll(more, confirm, cancel);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(20);
        bottom.setPadding(new Insets(20, 0, 0, 0));

    }

    public void setTriggers(CreateQuizPresenter presenter) {
        cancel.setOnAction(e -> {
            presenter.back();
        });

        more.setOnMouseClicked(e -> {
            if (cbxOptions.getValue() != null && cbxOptions.getValue().equals("Reutilizar Questões")) {
                presenter.reuseQuestion();
            } else {
                presenter.createQuestion();
            }
        });

        questionsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Question q = questionsView.getSelectionModel().getSelectedItem();
                if (q != null) {
                    presenter.viewQuestion(q);
                }
            }
        });

        confirm.setOnAction(e -> {
            presenter.createQuiz(categoty.getText(), name.getText());
        });

    }

    

    public String getName() {
        return name.getText();
    }

    public String getCategory() {
        return categoty.getText();
    }

    public void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Verificação");
        alert.setHeaderText("Criação/Edição de um quiz");
        alert.setContentText(info);
        alert.show();
    }
}
