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
import educationalquiz.view.ListViewer;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;

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

    public void createQuestion( URI source, Answer[] answers, String title) {
        if (checkString(title) && checkAnswers(answers)) {
            String url = source.toString();
            if (url == null || url.isEmpty()) {
                url = "/resources/interrogation.png";
            } else {
                url = copyFile(source);
            }
            Question question = new Question(answers, title, url);
            model.addQuestion(question);
            view.showInfo("Questão criada com sucesso!");
            back();
        } else {
            view.showInfo("Questão criada sem sucesso!"
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

    private Path toPath(String url) {
        Path path = null;
        try {
            URI uri = ClassLoader.getSystemResource(url).toURI();
            String mainPath = Paths.get(uri).toString();
            path = Paths.get(mainPath);
        } catch (URISyntaxException ex) {
            Logger.getLogger(CreateQuestionPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
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
        ListViewer view = new ListViewer((HashSet<Answer>) manager.getAnswers(), question);
        ListViewerPresenter p = new ListViewerPresenter(manager, view, model);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 650));
    }
}
