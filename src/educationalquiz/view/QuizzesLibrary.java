/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Quiz;
import educationalquiz.model.QuizManager;
import educationalquiz.presenter.QuizzesLibraryPresenter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author hugob
 */
public class QuizzesLibrary extends BorderPane {

    private ScrollPane scrollPane;
    private FlowPane flow;
    private HBox bottom;
    private VBox center;
    private Button btnBack;
    private QuizManager manager;
    private HashMap<Quiz, Button> quizes;
    private ComboBox cbxOptions;

    public QuizzesLibrary(QuizManager manager) {
        flow = new FlowPane();
        scrollPane = new ScrollPane();
        bottom = new HBox();
        center = new VBox();
        this.manager = manager;
        quizes = new HashMap<>();
        setupLayout();
    }

    private void setupLayout() {
        setupTop();
        setupCenter();
        setupBottom();
        setPadding(new Insets(10, 10, 10, 10));

    }

    private void setupTop() {
        Text text = new Text("Biblioteca de Quizs");
        text.setFont(Font.font(20));
        HBox hbox = new HBox(text);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10, 0, 20, 0));
        setTop(hbox);
    }

    private void setupCenter() {
        setupInformation();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scrollPane.setContent(flow);
        scrollPane.setPadding(new Insets(5, 5, 5, 5));
        scrollPane.setMaxSize(560, 450);
        scrollPane.setMinSize(560, 450);
        flow.setPadding(new Insets(5, 5, 5, 5));
        flow.setVgap(20);
        flow.setHgap(10);
        flow.setAlignment(Pos.TOP_LEFT);
        flow.setMinWidth(550);

        for (Quiz q : manager.getQuizzes()) {
            Button btn = generateButton(q);
            quizes.put(q, btn);
            flow.getChildren().add(btn);
        }
        center.getChildren().add(scrollPane);

        center.setPadding(new Insets(5, 5, 5, 5));
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);
        setCenter(center);
    }

    private void setupInformation() {
        Label lblQuestions = new Label("Pretende: ");
        cbxOptions = new ComboBox();
        cbxOptions.setItems(FXCollections.observableArrayList(
                "Visualizar Quiz", "Editar Quiz", "Eliminar Quiz"));
        cbxOptions.setPadding(new Insets(5, 5, 5, 5));
        lblQuestions.setPadding(new Insets(5, 5, 5, 50));
        HBox box = new HBox();
        box.getChildren().addAll(lblQuestions, cbxOptions);
        center.getChildren().add(box);
    }

    private void setupBottom() {
        ImageView img1 = new ImageView(new Image("/resources/back.png"));
        img1.setFitWidth(50);
        img1.setFitHeight(50);
        btnBack = new Button("", img1);
        bottom.getChildren().add(btnBack);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(10, 0, 0, 0));
        setBottom(bottom);
    }

    private Button generateButton(Quiz q) {
        Button btn = new Button(q.getCategory());
        btn.setMinWidth(100);
        btn.setMinHeight(100);
        btn.setMaxWidth(100);
        btn.setMaxHeight(100);
        Tooltip tt = new Tooltip(q.getName());
        btn.setTooltip(tt);
        tt.setStyle("-fx-background-color: white;"
                + "-fx-text-fill: black;");
        return btn;
    }

    public void setTriggers(QuizzesLibraryPresenter presenter) {
        btnBack.setOnAction(e -> {
            presenter.back();
        });

        for (Map.Entry<Quiz, Button> node : quizes.entrySet()) {
            node.getValue().setOnAction(e -> {
                if (cbxOptions.getValue() != null && cbxOptions.getValue().equals("Eliminar Quiz")) {
                    presenter.delete(node.getKey());
                } else if (cbxOptions.getValue() != null && cbxOptions.getValue().equals("Editar Quiz")) {
                    presenter.edit(node.getKey());
                } else {
                    presenter.enter(node.getKey());
                }

            });
        }
    }

    public boolean showConfirmation(String info) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Eliminação de um Quiz");
        alert.setContentText("Tem a certeza que pretende eliminar o quiz: " + info);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public void showInfo() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Eliminação de um Quiz");
        alert.setContentText("Quiz eliminado com sucesso!");
        alert.show();
    }

    public void refresh(Quiz q) {
        quizes.remove(q);
    }

}
