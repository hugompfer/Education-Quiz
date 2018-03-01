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
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hugob
 */
public class EditQuestionPresenter {

    private CreateQuestion view;
    private QuizManager manager;
    private Quiz model;
    private Question question;

    public EditQuestionPresenter(QuizManager manager, CreateQuestion view, Quiz model,Question question) {
        this.manager = manager;
        this.view = view;
        this.model = model;
        this.question=question;
        view.setTriggers(this);
    }

    public void editQuestion(URI source, Answer[] answers, String title) {
        if (checkString(title) && checkAnswers(answers)) {
            String url =source.toString();
            if (url == null || url.isEmpty()) {
                url = "/resources/interrogation.png";
            } else {
                url = copyFile(source);
            }
            question.update(title, url, answers);
            manager.updateQuiz(model);
            view.showInfo("Questão editada com sucesso!");
            back();
        } else {
            view.showInfo("Questão editada sem sucesso!"
                    + "\nVerifique se preencheu todos os campos.");
        }
    }
    
    public String copyFile(URI sourc) {
        File source=new File(sourc);
        File target=(new File(new File("src/images/").getAbsolutePath()+"/"+source.getName()));
        
        Path sourceFile = Paths.get(source.toURI());
        Path targetFile = Paths.get(target.toURI());
        try {
            Files.copy(sourceFile, targetFile,
                    StandardCopyOption.REPLACE_EXISTING);
          
            return "/images/"+target.getName();
        } catch (IOException ex) {
            view.showInfo("Houve um problema a copiar a imagem.");
            return "/resources/interrogation.png";
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
        EditQuizPresenter p = new EditQuizPresenter(manager, view, model);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }
}

