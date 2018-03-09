/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.presenter;

import educationalquiz.model.Quiz;
import educationalquiz.model.QuizGameplay;
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
        stage.setScene(new Scene(new InicialView(), 700, 700));
    }

    public void delete(Quiz quiz) {
        if (quiz != null) {
            if (view.showConfirmation(quiz.getCategory() + " - " + quiz.getName())) {
                if (manager.removeQuiz(quiz)) {
                    
                    view.showInfo();
                    refresh();
                }
            }
        }
    }

    private void refresh() {
        QuizzesLibrary view = new QuizzesLibrary(manager.getQuizzes());
        QuizzesLibraryPresenter p = new QuizzesLibraryPresenter(manager, view);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }

    public void enter(Quiz selected) {
        if (selected != null) {
            Stage stage = (Stage) view.getScene().getWindow();
            QuizGameplay qgp=new QuizGameplay(selected);
            QuizView view = new QuizView(qgp);
            QuizViewPresenter presenter = new QuizViewPresenter(manager, view, qgp);
            stage.setScene(new Scene(view, 700, 700));
        }
    }

    public void edit(Quiz selected) {
        if (selected != null) {
            CreateQuiz view = new CreateQuiz(selected,true);
            EditQuizPresenter p = new EditQuizPresenter(manager, view, selected);
            Stage stage = (Stage) this.view.getScene().getWindow();
            stage.setScene(new Scene(view, 700, 700));
        }
    }
}
