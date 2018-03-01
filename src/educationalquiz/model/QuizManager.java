/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hugob
 */
public class QuizManager {
    private QuizDAO quizs;
   
    public QuizManager(){
        quizs=new QuizDAOSerializable("");
        
    }
    
    public List<Quiz> getQuizzes(){
        return quizs.selectQuiz();
    }
    
    public Set<Question> getQuestions(){
        Set<Question> questions=new HashSet<>();
        for(Quiz q: quizs.selectQuiz()){
            questions.addAll(q.getQuestions());
        }
        return questions;
    }
    
    public Set<Answer> getAnswers(){
        Set<Answer> answers=new HashSet<>();
        for(Quiz q: quizs.selectQuiz()){
            answers.addAll(q.getAnswers());
        }
        return answers;
    }
    
    public boolean addQuiz(Quiz q){
        return quizs.insertQuiz(q);
    }

    public boolean removeQuiz(Quiz quiz) {
       return quizs.removeQuiz(quiz);
    }

    public boolean updateQuiz(Quiz model) {
        return quizs.updateQuiz(model);
    }
     
}
