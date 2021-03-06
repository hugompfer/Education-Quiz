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
import educationalquiz.view.ReuseQuestion;
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
        }else{
            view.showInfo("Verifique se tem todos os campos preenchidos.");
        }
            
    }

    public void createQuestion() {
        CreateQuestion view = new CreateQuestion();
        CreateQuestionPresenter p = new CreateQuestionPresenter(manager, view, model,this.view.getCategory(),this.view.getName(),this.view.isEdit());
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }


    public void reuseQuestion() {
        ReuseQuestion view = new ReuseQuestion(manager.getQuestionsTitle(), new Question());
        ListViewerPresenter p = new ListViewerPresenter(manager, view, model,this.view.getCategory(),this.view.getName(),this.view.isEdit());
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 650));
    }

    public void viewQuestion(Question selected) {
        if (selected != null) { 
            CreateQuestion view = new CreateQuestion(selected);
            EditQuestionPresenter p = new EditQuestionPresenter(manager, view, model, selected, this.view.getCategory(),this.view.getName(),this.view.isEdit());
            Stage stage = (Stage) this.view.getScene().getWindow();
            stage.setScene(new Scene(view, 700, 700));
        }
    }

    public void back() {
        Stage stage = (Stage) view.getScene().getWindow();
        stage.setScene(new Scene(new InicialView(), 700, 700));
    }

    public QuizManager getManager() {
        return manager;
    }

    public void delete(Question selected) {
        if (selected != null) {
            if (view.showConfirmation(model.getCategory() + " - " + model.getName())) {
                if (model.removeQuestion(selected)) {
                    view.showInfo();
                    refresh();
                }
            }
        }
    }

    private void refresh() {
        CreateQuiz view = new CreateQuiz(model,this.view.getCategory(),this.view.getName(),this.view.isEdit());
        CreateQuizPresenter p = new CreateQuizPresenter(manager, view, model);
        Stage stage = (Stage) this.view.getScene().getWindow();
        stage.setScene(new Scene(view, 700, 700));
    }

}
