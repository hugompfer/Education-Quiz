/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.presenter;


import educationalquiz.model.Quiz;
import educationalquiz.model.QuizManager;
import educationalquiz.view.CreateQuiz;


/**
 *
 * @author hugob
 */
public class EditQuizPresenter extends CreateQuizPresenter {

    public EditQuizPresenter(QuizManager manager, CreateQuiz view, Quiz model) {
        super(manager, view, model);
    }

    @Override
    public void createQuiz(String category, String name) {
        if (!(category.isEmpty() && name.isEmpty())) {
            super.getModel().setCategory(category);
            super.getModel().setName(name);
            back();
        }
    }
}
