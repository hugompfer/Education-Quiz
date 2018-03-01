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
import educationalquiz.view.InicialView;
import educationalquiz.view.ListViewer;
import java.util.HashSet;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class CreateQuestionPresenter {

    private CreateQuestion view;
    private QuizManager manager;
    private Quiz model;

    public CreateQuestionPresenter(QuizManager manager, CreateQuestion view, Quiz model) {
        this.manager = manager;
        this.view = view;
        this.model = model;
        view.setTriggers(this);
    }

    public void createQuestion(String url, Answer[] answers, String title) {
        if (checkString(title) && checkAnswers(answers)) {
            String newUrl = url == null || url.isEmpty() ? "/resources/interrogation.png" : url;
            Question question = new Question(answers, title, newUrl);
            model.addQuestion(question);
            view.showInfo("Questão criada com sucesso!");
            back();
        } else {
            view.showInfo("Questão criada sem sucesso!"
                    + "\nVerifique se preencheu todos os campos.");
        }
    }

    private boolean checkString(String info) {
        return info != null && !info.isEmpty();
    }

    private boolean checkAnswers(Answer[] answers) {
        if (answers != null) {
            int cont = 0;
            for (int i = 0; i < answers.length; i++) {
                if (answers[i].isCorrect()) {
                    cont++;
                }
            }
            return cont == 1;
        }
        return false;
    }

    public void back() {
        CreateQuiz view = new CreateQuiz(manager, model);
        CreateQuizPresenter p = new CreateQuizPresenter(manager, view, model);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }

    public void reuseQuestion(Question question) {
        ListViewer view = new ListViewer((HashSet<Answer>) manager.getAnswers(),question);
        ListViewerPresenter p = new ListViewerPresenter(manager, view, model);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }
}
