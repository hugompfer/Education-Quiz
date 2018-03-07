/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

/**
 *
 * @author hugob
 */
public class QuizGameplay {

    private Quiz quiz;
    private int position;

    public QuizGameplay(Quiz quiz) {
        this.quiz = quiz;
        this.position = 0;
    }

    public Question nextQuestion() {
        if ((position + 1) > quiz.numberOfQuestions() - 1) {
            return null;
        }
        return quiz.getQuestion(++position);
    }

    public Question previousQuestion() {

        if ((position - 1) < 0) {
            return null;
        }
        return quiz.getQuestion(--position);
    }

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
