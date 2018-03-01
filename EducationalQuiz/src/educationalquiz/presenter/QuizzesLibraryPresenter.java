/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.presenter;

import educationalquiz.model.Quiz;
import educationalquiz.model.QuizManager;
import educationalquiz.view.CreateQuiz;
import educationalquiz.view.InicialView;
import educationalquiz.view.QuizView;
import educationalquiz.view.QuizzesLibrary;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class QuizzesLibraryPresenter {

    private QuizzesLibrary view;
    private QuizManager manager;

    public QuizzesLibraryPresenter(QuizManager manager, QuizzesLibrary view) {
        this.manager = manager;
        this.view = view;
        view.setTriggers(this);
    }

    public void back() {
        Stage stage = (Stage) view.getScene().getWindow();
        stage.setScene(new Scene(new InicialView(manager), 700, 700));
    }

    public void delete(Quiz quiz) {
        if (view.showConfirmation(quiz.getCategory() + " - " + quiz.getName())) {
            if (manager.removeQuiz(quiz)) {
                view.showInfo();
                view.refresh(quiz);
                refresh();
            }
        }
    }

    private void refresh() {
        QuizzesLibrary view = new QuizzesLibrary(manager);
        QuizzesLibraryPresenter p = new QuizzesLibraryPresenter(manager, view);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 650));
    }

    public void enter(Quiz quiz) {
        Stage stage = (Stage) view.getScene().getWindow();
        QuizView view = new QuizView(quiz);
        QuizViewPresenter presenter = new QuizViewPresenter(manager, view, quiz);
        stage.setScene(new Scene(view, 700, 700));
    }

    public void edit(Quiz quiz) {
        CreateQuiz view = new CreateQuiz(manager, quiz);
        EditQuizPresenter p = new EditQuizPresenter(manager, view, quiz);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }
}
