/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.Serializable;

/**
 * Represents an answer having your information and whether it is correct or not
 *
 * @author hugob
 */
public class Answer implements Serializable {

    private String information;
    private boolean correct;

    /**
     * Create an answer with the information and if is correct or not
     *
     * @param information information of answer
     * @param correct is a correct answer or not
     */
    public Answer(String information, boolean correct) {
        this.information = information;
        this.correct = correct;
    }

    /**
     * Returns the answer information
     *
     * @return information
     */
    public String getInformation() {
        return information;
    }

    /**
     * Returns if answer is correct or not
     *
     * @return information
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * Returns a string representation of this answer.
     * @return information
     */
    @Override
    public String toString() {
        return information;
    }
}
