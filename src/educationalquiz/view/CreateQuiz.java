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
import java.awt.Color;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 *
 * @author hugob
 */
public class CreateQuiz extends BorderPane {

    private ListView<Question> questionsView;
    private TextField txtName;
    private TextField txtCategoty;
    private Button btnConfirm;
    private Button btnBack;
    private Button btnAdd;
    private Button btnEdit;
    private Button btnDelete;
    private ComboBox cbxOptions;
    private boolean edit;

    public CreateQuiz(boolean edit) {
        this.edit = edit;
        setupButtons();
        questionsView = new ListView<>();
        txtName = new TextField();
        txtCategoty = new TextField();
        setupLayout();
        disableButton(true);
        

    }

    public CreateQuiz(Quiz quiz, boolean edit) {
        this(edit);
        ObservableList<Question> observableArrayList
                = FXCollections.observableArrayList(quiz.getQuestions());
        questionsView.setItems(observableArrayList);
        txtCategoty.setText(quiz.getCategory());
        txtName.setText(quiz.getName());

    }

    public CreateQuiz(Quiz model, String updateCategory, String updateName, boolean edit) {
        this(model,edit);
        txtCategoty.setText(updateCategory);
        txtName.setText(updateName);
    }

    private void setupStyles() {
        getStylesheets().add("css/Library.css");
        setId("root");
        btnAdd.setId("optionsButton");
        btnDelete.setId("optionsButton");
        btnEdit.setId("optionsButton");
        btnBack.setId("btnBack");
        btnConfirm.setId("btnConfirm");
        questionsView.getStylesheets().add("css/list.css");

    }

    private void setupButtons() {
        btnBack = new Button();
        ImageView iVAdd = new ImageView(new Image("/resources/add.png"));
        iVAdd.setId("imageViewOptionsButtons");
        ImageView iVEdit = new ImageView(new Image("/resources/edit.png"));
        iVEdit.setId("imageViewOptionsButtons");
        ImageView iVDelete = new ImageView(new Image("/resources/delete.png"));
        iVDelete.setId("imageViewOptionsButtons");
        btnConfirm = new Button("Concluir");
        btnAdd = new Button("", iVAdd);
        btnEdit = new Button("", iVEdit);
        btnDelete = new Button("", iVDelete);
    }

    private void setupTop() {
        Text text = new Text(edit == false ? "Novo Quiz" : "Edição de Quiz");
        text.setId("title");
        HBox hbox = new HBox();
        hbox.setId("topBox");
        hbox.getChildren().addAll(btnBack, text);
        setTop(hbox);
    }

    private void setupLayout() {
        setupStyles();
        inicializeBottomButtons();
        inicializeCenter();
        setupTop();
    }

    private void inicializeCenter() {
        cbxOptions = new ComboBox();
        cbxOptions.setItems(FXCollections.observableArrayList(
                "Reutilizar Questões", "Criar novas questões"));
        cbxOptions.getStylesheets().add("css/combobox.css");

        HBox buttonsBox = new HBox();
        buttonsBox.setId("buttonsBox");
        buttonsBox.getChildren().addAll(btnAdd, btnEdit, btnDelete);

        VBox cbxOp = new VBox();
        cbxOp.setId("cbxOptionsBox");
        cbxOp.getChildren().addAll(cbxOptions);

        HBox lastOptions = new HBox();
        lastOptions.setId("optionsBoxWithCbx");
        lastOptions.getChildren().addAll(cbxOp, buttonsBox);

        VBox txtBoxName = new VBox();
        txtBoxName.setId("textfieldBox");
        txtBoxName.getChildren().addAll(new Label("Nome"), txtName);

        VBox txtBoxCategory = new VBox();
        txtBoxCategory.setId("textfieldBox");
        txtBoxCategory.getChildren().addAll(new Label("Categoria"), txtCategoty);

        HBox textFields = new HBox();
        textFields.setId("CategoryNameBox");
        textFields.getChildren().addAll(txtBoxName, txtBoxCategory);

        VBox questionsArea = new VBox();
        questionsArea.setPadding(new Insets(10, 0, 0, 0));
        questionsArea.setAlignment(Pos.CENTER);
        questionsArea.getChildren().add(questionsView);

        VBox box = new VBox();
        box.getChildren().addAll(textFields, lastOptions, questionsArea);
        setCenter(box);
    }

    private void inicializeBottomButtons() {
        HBox bottom = new HBox();
        bottom.setId("bottom");
        bottom.getChildren().add(btnConfirm);
        setBottom(bottom);
    }

    private void disableButton(boolean disable) {
        btnDelete.setDisable(disable);
        btnEdit.setDisable(disable);
    }

    public void setTriggers(CreateQuizPresenter presenter) {
        btnBack.setOnAction(e -> {
            presenter.back();
        });

        questionsView.setOnMouseClicked((MouseEvent event) -> {
            disableButton(false);
        });

        btnAdd.setOnMouseClicked(e -> {
            if (cbxOptions.getValue() != null && cbxOptions.getValue().equals("Reutilizar Questões")) {
                presenter.reuseQuestion();
            } else {
                presenter.createQuestion();
            }
        });

        btnEdit.setOnAction(e -> {
            Question selected = questionsView.getSelectionModel().getSelectedItem();
            presenter.viewQuestion(selected);
        });

        btnDelete.setOnAction(e -> {
            Question selected = questionsView.getSelectionModel().getSelectedItem();
            presenter.delete(selected);
        });

        btnConfirm.setOnAction(e -> {
            presenter.createQuiz(txtCategoty.getText(), txtName.getText());
        });

    }

    public boolean isEdit() {
        return edit;
    }
    
    public String getName() {
        return txtName.getText();
    }

    public String getCategory() {
        return txtCategoty.getText();
    }

    public void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Criação/Edição de um quiz");
        alert.setContentText(info);
        alert.show();
    }

    public boolean showConfirmation(String info) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Eliminação de uma questão");
        alert.setContentText("Tem a certeza que pretende eliminar o questão: " + info+" ?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public void showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Eliminação de uma questão");
        alert.setContentText("Questão eliminada com sucesso!");
        alert.show();
    }
}
