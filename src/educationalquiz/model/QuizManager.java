/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the manager of quizs, with function of persistance of quizzes
 *
 * @author hugob
 */
public class QuizManager {

    private QuizDAO quizs;

    /**
     * Create a QuizManager with the json persistance
     *
     */
    public QuizManager() {
        quizs = new QuizDAOJSON("");
    }

    public List<Quiz> getQuizzes() {
        return quizs.selectQuiz();
    }

    /**
     * Returns all the questions title non repetead, of all quizzes
     *
     * @return list of questions titles
     */
    public Set<String> getQuestionsTitle() {
        Set<String> questions = new HashSet<>();
        for (Quiz q : quizs.selectQuiz()) {
            questions.addAll(q.getQuestionsTitle());
        }
        return questions;
    }

    /**
     * Returns all the questions non repetead, of all quizzes
     *
     * @return list of questions
     */
    public Set<Answer> getAnswers() {
        Set<Answer> answers = new HashSet<>();
        for (Quiz q : quizs.selectQuiz()) {
            answers.addAll(q.getAnswers());
        }
        return answers;
    }

    /**
     * Add to the persistance the quiz
     *
     * @return true if was sucefull/false the opposite
     */
    public boolean addQuiz(Quiz q) {
        quizs.removeQuiz(q);
        return quizs.insertQuiz(q);
    }

    /**
     * remove the quiz in the persistance
     *
     * @param quiz quiz to remove
     * @return true if was a sucess /false the opposite
     */
    public boolean removeQuiz(Quiz quiz) {
        if (quizs.removeQuiz(quiz)) {
            for (Question q : quiz.getQuestions()) {
                String url = q.getImageURL();
                if (url != null) {
                    new File(url).delete();
                }
            }
            return true;
        }
        return false;
    }

    /**
     * update the quiz in the persistance
     *
     * @param model quiz to update
     * @param category new category
     * @param name new name
     * @return true if was a sucess /false the opposite
     */
    public boolean updateQuiz(Quiz model, String category, String name) {
        return quizs.updateQuiz(model, category, name);
    }

    /**
     * update the quiz in the persistance
     *
     * @param model quiz to update
     * @return true if was a sucess /false the opposite
     */
    public boolean updateQuiz(Quiz model) {
        return quizs.updateQuiz(model);
    }
}
