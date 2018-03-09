/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.presenter;

import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import educationalquiz.model.Quiz;
import educationalquiz.model.QuizManager;
import educationalquiz.view.CreateQuestion;
import educationalquiz.view.CreateQuiz;
import educationalquiz.view.ReuseQuestion;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class ListViewerPresenter {

    private ReuseQuestion view;
    private QuizManager manager;
    private Quiz model;
    private String updateCategory;
    private String updateName;
    private boolean edit;
    
    public ListViewerPresenter(QuizManager manager, ReuseQuestion view, Quiz model, String updateCategory, String updateName,boolean edit) {
        this.manager = manager;
        this.view = view;
        this.model = model;
        this.updateCategory=updateCategory;
        this.updateName=updateName;
        this.edit=edit;
        view.setTriggers(this);
    }

    public void back() {
        CreateQuiz view = new CreateQuiz(model,edit);
        CreateQuizPresenter p = new CreateQuizPresenter(manager, view, model);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));

    }

    public void enterQuestion(String title, Question question) {
        question.setTitle(title);
        CreateQuestion view = new CreateQuestion(question);
        CreateQuestionPresenter p = new CreateQuestionPresenter(manager, view, model,updateCategory,updateName,edit);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }

    public void enterAnswer(String Information, Question question) {
        question.addAnswer(new Answer(Information, false));
        CreateQuestion view = new CreateQuestion(question);
        CreateQuestionPresenter p = new CreateQuestionPresenter(manager, view, model,updateCategory,updateName,edit);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));

    }
}
