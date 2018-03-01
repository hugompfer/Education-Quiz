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
import educationalquiz.view.QuizView;
import educationalquiz.view.QuizzesLibrary;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugoferreira
 */
public class QuizViewPresenter {

    private QuizView view;
    private QuizManager manager;
    private Quiz model;

    public QuizViewPresenter(QuizManager manager, QuizView view, Quiz model) {
        this.manager = manager;
        this.view = view;
        this.model = model;
        view.setTriggers(this);
    }

    public void nextQuestion() {
        Question question = model.nextQuestion();
        if (question != null) {
            view.setupQuestionLayout(question);
        }
    }

    public void previousQuestion() {
        Question question = model.previousQuestion();
        if (question != null) {
            view.setupQuestionLayout(question);
        }
    }

    public void checkAnswer(Answer answer) {
        if (answer.isCorrect()) {
            view.paintCorrectButton(answer);
        } else {
            view.paintWrongButton(answer);
        }
    }

    public void back() {
        QuizzesLibrary view = new QuizzesLibrary(manager);
        QuizzesLibraryPresenter p = new QuizzesLibraryPresenter(manager, view);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 650));
    }

}
