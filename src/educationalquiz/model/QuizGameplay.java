/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

/**
 * Represents the logical quiz gameplay
 * @author hugob
 */
public class QuizGameplay {

    private Quiz quiz;
    private int position;

    /**
     * Create a QuizGameplay with the quiz information
     *
     */
    public QuizGameplay(Quiz quiz) {
        this.quiz = quiz;
        this.position = 0;
    }

    /**
     * Returns the next question if exists
     *
     * @return Question if exists/null opposite
     */
    public Question nextQuestion() {
        if ((position + 1) > quiz.getNumberOfQuestions() - 1) {
            return null;
        }
        return quiz.getQuestion(++position);
    }

    /**
     * Returns the previous question if exists
     *
     * @return Question if exists/null opposite
     */
    public Question previousQuestion() {

        if ((position - 1) < 0) {
            return null;
        }
        return quiz.getQuestion(--position);
    }

    /**
     * Returns the atual question 
     *
     * @return atual question
     */
    public Question getAtualQuestion() {
        return quiz.getQuestion(position);
    }

    
    public String getQuizCategory() {
        return quiz.getCategory();
    }
    
    public String getQuizName() {
        return quiz.getName();
    }
    
    public Quiz getQuiz() {
        return quiz;
    }
}
