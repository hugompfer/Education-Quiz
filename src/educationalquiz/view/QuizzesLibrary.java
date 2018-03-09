/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Quiz;
import educationalquiz.presenter.QuizzesLibraryPresenter;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;


/**
 *
 * @author hugob
 */
public class QuizzesLibrary extends BorderPane {

    private ListView<Quiz> quizzesList;
    private Button btnBack;
    private Button btnView;
    private Button btnEdit;
    private Button btnDelete;
    private TextField filter;

    public QuizzesLibrary(List<Quiz> quizzes) {
        setupButtons();
        filter = new TextField();
        quizzesList = new ListView<>(inicializeFilteredList(quizzes));
        quizzesList.setPlaceholder(new Label("Nenhum quiz disponível"));
        disableButton(true);
        setupLayout();
    }

    private FilteredList inicializeFilteredList(List<Quiz> quizzes) {
        ObservableList<Quiz> data = FXCollections.observableArrayList(quizzes);
        FilteredList<Quiz> filteredData = new FilteredList<>(data, s -> true);
        filter.textProperty().addListener(obs -> {
            String f = filter.getText();
            if (f == null || f.length() == 0) {
                filteredData.setPredicate(s -> true);
            } else {
                filteredData.setPredicate(s -> s.containsCategory(f) || s.containsName(f) || s.containsAll(f));
            }
        });
        return filteredData;
    }

    private void setupLayout() {
        setupTop();
        setupCenter();
        setupStyles();
    }

    private void setupStyles() {
        getStylesheets().add("css/Library.css");
        setId("root");
        btnView.setId("optionsButton");
        btnDelete.setId("optionsButton");
        btnEdit.setId("optionsButton");
        filter.setId("txtFilter");
        quizzesList.getStylesheets().add("css/list.css");
    }

    private void setupTop() {
        Text text = new Text("BIBLIOTECA DE QUIZZES");
        text.setId("title");
        HBox hbox = new HBox();
        hbox.setId("topBox");
        hbox.getChildren().addAll(btnBack, text);
        setTop(hbox);
    }

    private void setupCenter() {
        HBox box = setupInformation();
        VBox center = new VBox();
        center.setId("center");
        center.getChildren().addAll(box, quizzesList);
        setCenter(center);
    }

    private HBox setupInformation() {
        HBox buttonsBox = new HBox();
        buttonsBox.setId("buttonsBox");
        buttonsBox.getChildren().addAll(btnView, btnEdit, btnDelete);

        VBox txtBox = new VBox();
        txtBox.setId("textfieldBox");
        txtBox.getChildren().addAll(new Label("Pesquisar quiz"), filter);

        HBox box = new HBox();
        box.setId("optionsBox");
        box.getChildren().addAll(txtBox, buttonsBox);
        return box;
    }

    private void setupButtons() {
        btnBack = new Button();
        btnBack.setId("btnBack");
        ImageView iVAdd = new ImageView(new Image("/resources/eye.png"));
        iVAdd.setId("imageViewOptionsButtons");
        ImageView iVEdit = new ImageView(new Image("/resources/edit.png"));
        iVEdit.setId("imageViewOptionsButtons");
        ImageView iVDelete = new ImageView(new Image("/resources/delete.png"));
        iVDelete.setId("imageViewOptionsButtons");
        btnView = new Button("", iVAdd);
        btnEdit = new Button("", iVEdit);
        btnDelete = new Button("", iVDelete);
    }

    private void disableButton(boolean disable) {
        btnDelete.setDisable(disable);
        btnEdit.setDisable(disable);
        btnView.setDisable(disable);
    }

    public void setTriggers(QuizzesLibraryPresenter presenter) {

        btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            presenter.back();
        });

        quizzesList.setOnMouseClicked((MouseEvent event) -> {
            disableButton(false);
        });

        btnView.setOnAction(e -> {
            Quiz selected = quizzesList.getSelectionModel().getSelectedItem();
            presenter.enter(selected);
        });

        btnEdit.setOnAction(e -> {
            Quiz selected = quizzesList.getSelectionModel().getSelectedItem();
            presenter.edit(selected);
        });

        btnDelete.setOnAction(e -> {
            Quiz selected = quizzesList.getSelectionModel().getSelectedItem();
            presenter.delete(selected);
        });

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

}
