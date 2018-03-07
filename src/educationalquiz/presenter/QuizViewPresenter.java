/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.presenter;

import educationalquiz.model.Answer;
import educationalquiz.model.Question;
import educationalquiz.model.Quiz;
import educationalquiz.model.QuizGameplay;
import educationalquiz.model.QuizManager;
import educationalquiz.model.Sound;
import educationalquiz.view.QuizView;
import educationalquiz.view.QuizzesLibrary;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugoferreira
 */
public class QuizViewPresenter {

    private QuizView view;
    private QuizManager manager;
    private QuizGameplay model;
    private Sound correct;
    private Sound wrong;

    public QuizViewPresenter(QuizManager manager, QuizView view, QuizGameplay model) {
        this.manager = manager;
        this.view = view;
        this.model = model;
        correct = new Sound("/sounds/correct.mp3");
        wrong = new Sound("/sounds/wrong.mp3");
        view.setTriggers(this);
    }

    public void nextQuestion() {
        Question question = model.nextQuestion();
        if (question != null) {
            view.setupQuestionLayout(question);
        } else {
            if (view.showInfo()) {
                back();
            }
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
            correct.play();
            correct.recreate();
            waitMoment();
        } else {
            view.paintWrongButton(answer);
            wrong.play();
            wrong.recreate();

        }
    }

    private void waitMoment() {
        Timer delay = new Timer();
        delay.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    nextQuestion();
                    delay.cancel();
                });
            }
        }, 2000);
    }

    public void back() {
        QuizzesLibrary view = new QuizzesLibrary(manager.getQuizzes());
        QuizzesLibraryPresenter p = new QuizzesLibraryPresenter(manager, view);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 650));
    }

}
