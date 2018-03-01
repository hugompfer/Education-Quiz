/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author hugob
 */
public class Quiz implements Serializable {

    private List<Question> questions;
    private String category;
    private String name;
    private int index;

    public Quiz() {
        this("", "");
    }

    public Quiz(String category, String name) {
        this.questions = new ArrayList<>();
        this.category = category;
        this.name = name;
        index = 0;
    }

    public Quiz(List<Question> questions, String category, String name) {
        this.questions = questions;
        this.category = category;
        this.name = name;
        index = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public Question nextQuestion() {

        if ((index+1) > questions.size() - 1) {
            return null;
        }
        return questions.get(++index);
    }

    public Question previousQuestion() {

        if ((index-1) < 0) {
            return null;
        }
        return questions.get(--index);
    }

    public Question getAtualQuestion() {
        return questions.get(index);
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    
    public List<Answer> getAnswers() {
        List<Answer> answers=new ArrayList<>();
        for(Question a: questions){
            answers.addAll((Collection<? extends Answer>) a.getAnswers());
        }
        return answers;
    }
}
