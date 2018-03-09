/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import educationalquiz.presenter.ListViewerPresenter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author hugob
 */
public class ReuseQuestion extends BorderPane {

    private ListView<String> listQuestion;
    private TextField filter;
    private Button btnBack;
    private Question question;

    public ReuseQuestion(Set<String> listQuestions, Question question) {
        filter = new TextField();
        listQuestion = new ListView<>(inicializeFilteredList(listQuestions));
        listQuestion.setPlaceholder(new Label("Nenhuma pergunta disponível"));
        this.question = question;
        btnBack = new Button();
        setupLayout();
    }

    private FilteredList inicializeFilteredList(Set<String> listQuestions) {
        ObservableList<String> data = FXCollections.observableArrayList(new ArrayList<>(listQuestions));
        FilteredList<String> filteredData = new FilteredList<>(data, s -> true);
        filter.textProperty().addListener(obs -> {
            String f = filter.getText();
            if (f == null || f.length() == 0) {
                filteredData.setPredicate(s -> true);
            } else {
                filteredData.setPredicate(s -> s.contains(f));
            }
        });
        return filteredData;
    }

    private void setupStyles() {
        getStylesheets().add("css/Library.css");
        setId("root");
        btnBack.setId("btnBack");
        filter.setId("txtFilter");
        listQuestion.getStylesheets().add("css/list.css");
    }

    private void setupLayout() {
        setupStyles();
        setupTop();
        inicializeCenter();

    }

    private void setupTop() {
        Text text = new Text("Reutilizar questão");
        text.setId("title");
        HBox hbox = new HBox();
        hbox.setId("topBox");
        hbox.getChildren().addAll(btnBack, text);
        setTop(hbox);
    }

    private void inicializeCenter() {
        VBox txtBox = new VBox();
        txtBox.setId("reuseFilterBox");
        txtBox.getChildren().addAll(new Label("Pesquisar quiz"), filter);

        VBox vbox = new VBox();
        vbox.setId("center");
        vbox.getChildren().add(listQuestion);

        VBox center = new VBox();
        center.getChildren().addAll(txtBox, vbox);
        center.setId("resuseBox");
        setCenter(center);

    }

    public void setTriggers(ListViewerPresenter presenter) {
        btnBack.setOnAction(e -> {
            presenter.back();
        });

        listQuestion.setOnMouseClicked((MouseEvent event) -> {
            if (listQuestion.getSelectionModel().getSelectedItem() != null) {
                presenter.enterQuestion(listQuestion.getSelectionModel().getSelectedItem(), question);
            }
        });

    }

}
