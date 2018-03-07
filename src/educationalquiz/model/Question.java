/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author hugob
 */
public class Question implements Serializable {

    private String title;
    private Answer[] answers;
    private String imageURL;

    public Question() {
        this(null);
    }

    public Question(String title) {
        this(title, null);
    }

    public Question(String title, String url) {
        answers = new Answer[4];
        this.title = title;
        imageURL = url;
    }

    public Question(Answer[] answers, String title, String url) {
        this.answers = new Answer[4];
        this.title = title;
        imageURL = url;
        this.answers = copyAnswer(answers);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Iterable<String> getAnswersInformation() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) {
            list.add(answers[i].getInformation());
        }
        return list;
    }

    public Iterable<Answer> getAnswers() {
        ArrayList<Answer> list = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) {
            list.add(answers[i]);
        }
        return list;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    private Answer[] copyAnswer(Answer[] answers) {
        Answer[] answer = new Answer[answers.length];
        for (int i = 0; i < answers.length; i++) {
            answer[i] = answers[i];
        }
        return answer;
    }

    public void update(String title, String url, Answer[] answers) {
        this.title = title;
        this.imageURL = url;
        this.answers = copyAnswer(answers);
    }

    public Answer getCorrectAnswer() {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].isCorrect()) {
                return answers[i];
            }
        }
        return null;
    }

    public void addAnswer(Answer answer) {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == null) {
                answers[i] = answer;
                break;
            }
        }
    }

    public void addAnswers(Answer[] answers) {
        copyAnswer(answers);
    }

    public boolean containsTitle(String f) {
        return title.toLowerCase().contains(f.toLowerCase());
    }
    
    @Override
    public String toString() {
        return title;
    }

    

}
