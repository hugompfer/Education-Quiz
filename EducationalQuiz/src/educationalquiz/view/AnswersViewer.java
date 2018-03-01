/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.view;

import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author hugob
 */
public class AnswersViewer extends VBox {

    private HBox top;
    private VBox center;
    private HBox bottom;
    private ListView<Question> list;
    private TextField filter;
    private Button back;

    public AnswersViewer(List<Answer> listAnswer) {
        top = new HBox();
        center = new VBox();
        bottom = new HBox();
        list = new ListView<>();
        //list.setItems((ObservableList<Answer>) listAnswer);
        filter = new TextField();
        setupLayout();
    }

    private void setupLayout() {
        inicializeTop();
        inicializeCenter();
        inicializeBottom();

        getChildren().addAll(top, center, bottom);
    }

    private void inicializeTop() {
        Text text = new Text("Lista de Respostas");
        text.setFont(new Font(20));
        top.getChildren().add(text);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(10, 0, 0, 0));
    }

    private void inicializeCenter() {
        Label lblFilter = new Label("Pesquise a resposta desejada:");
        HBox box = new HBox();
        filter.setMinSize(300, 25);
        filter.setMaxSize(300, 25);
        list.setMaxSize(550, 350);
        list.setMinSize(550, 350);

        box.getChildren().addAll(lblFilter, filter);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(0, 0, 10, 0));
        box.setSpacing(90);

        center.getChildren().addAll(box, list);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(20, 0, 20, 0));
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

}
