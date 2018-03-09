/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a question encapsulate the title, image and respective answers
 *
 * @author hugob
 */
public class Question implements Serializable {

    private String title;
    private Answer[] answers;
    private String imageURL;

    /**
     * Create a question with no information
     *
     */
    public Question() {
        this(null);
    }

    /**
     * Create a question with the title information
     *
     * @param title title of question
     */
    public Question(String title) {
        this(title, null);
    }

    /**
     * Create a question with the title and image information
     *
     * @param title title of question
     * @param url image url of question
     */
    public Question(String title, String url) {
        answers = new Answer[4];
        this.title = title;
        imageURL = url;
    }

    /**
     * Create a question with the title, image and answers information
     *
     * @param answers anwers of question
     * @param title title of question
     * @param url image url of question
     */
    public Question(Answer[] answers, String title, String url) {
        this.answers = new Answer[4];
        this.title = title;
        imageURL = url;
        this.answers = copyAnswer(answers);
    }

    // make a copy of group of anwser returning a new one
    private Answer[] copyAnswer(Answer[] answers) {
        Answer[] answer = new Answer[answers.length];
        for (int i = 0; i < answers.length; i++) {
            answer[i] = answers[i];
        }
        return answer;
    }

    /**
     * Returns the title information
     *
     * @return information
     */
    public String getTitle() {
        return title;
    }

    /**
     * Modify the title information
     *
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the information of every answer of this question
     *
     * @return information of every answer
     */
    public Iterable<String> getAnswersInformation() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) {
            list.add(answers[i].getInformation());
        }
        return list;
    }

    /**
     * Returns every answer of this question
     *
     * @return information of every answer
     */
    public Iterable<Answer> getAnswers() {
        ArrayList<Answer> list = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) {
            list.add(answers[i]);
        }
        return list;
    }

    /**
     * Returns the title information
     *
     * @return information
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Modify the image url
     *
     * @param imageURL new image url
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Modify all atributes passed by paremeter
     *
     * @param title new title
     * @param url new url image
     * @param answers new answers
     */
    public void update(String title, String url, Answer[] answers) {
        this.title = title;
        this.imageURL = url;
        this.answers = copyAnswer(answers);
    }

    /**
     * Add a new answer
     *
     * @param answer answer to add
     */
    public void addAnswer(Answer answer) {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == null) {
                answers[i] = answer;
                break;
            }
        }
    }

    /**
     * Returns a string representation of this answer.
     *
     * @return information
     */
    @Override
    public String toString() {
        return title;
    }

}
