/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.presenter;

import educationalquiz.model.Question;
import educationalquiz.model.Quiz;
import educationalquiz.model.QuizManager;
import educationalquiz.view.CreateQuestion;
import educationalquiz.view.CreateQuiz;
import educationalquiz.view.InicialView;
import educationalquiz.view.ListViewer;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class CreateQuizPresenter {

    private CreateQuiz view;
    private QuizManager manager;
    private Quiz model;

    public CreateQuizPresenter(QuizManager manager, CreateQuiz view, Quiz model) {
        this.manager = manager;
        this.view = view;
        this.model = model;
        view.setTriggers(this);
    }

    public void createQuiz(String category, String name) {
        if (!(category.isEmpty() && name.isEmpty())) {
            model.setCategory(category);
            model.setName(name);
            if (manager.addQuiz(model)) {
                back();
            }
        }
    }

    public void createQuestion() {
        updateQuiz();
        CreateQuestion view = new CreateQuestion(manager);
        CreateQuestionPresenter p = new CreateQuestionPresenter(manager, view, model);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }

    private void updateQuiz() {
        model.setCategory(view.getCategory());
        model.setName(view.getName());
    }

    public void reuseQuestion() {
        updateQuiz();
        ListViewer view = new ListViewer(manager.getQuestions(), new Question());
        ListViewerPresenter p = new ListViewerPresenter(manager, view, model);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 650));
    }

    public void viewQuestion(Question q) {
        updateQuiz();
        CreateQuestion view = new CreateQuestion(q, manager);
        EditQuestionPresenter p = new EditQuestionPresenter(manager, view, model, q);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }

    public void back() {
        Stage stage = (Stage) view.getScene().getWindow();
        stage.setScene(new Scene(new InicialView(manager), 700, 700));
    }

    public Quiz getModel() {
        return model;
    }
}
