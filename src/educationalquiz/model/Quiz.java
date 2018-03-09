/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a quiz with a category, name and a group of questions
 *
 * @author hugob
 */
public class Quiz implements Serializable {

    private List<Question> questions;
    private String category;
    private String name;

    /**
     * Create a quiz with no information
     *
     */
    public Quiz() {
        this("", "");
    }

    /**
     * Create a quiz with the category and name information
     *
     * @param category quiz category
     * @param name quiz name
     */
    public Quiz(String category, String name) {
        this.questions = new ArrayList<>();
        this.category = category;
        this.name = name;

    }

    /**
     * Create a quiz with the category, name and a group of questions
     * information
     *
     * @param questions quiz questions
     * @param category quiz category
     * @param name quiz name
     */
    public Quiz(List<Question> questions, String category, String name) {
        this.questions = questions;
        this.category = category;
        this.name = name;
    }

    /**
     * Modify the quiz name
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modify the quiz category
     *
     * @param category new category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Add a question to current group of questions
     *
     * @param question new question
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }

    /**
     * Remove a question from current group of questions
     *
     * @param question question to remove
     * @return true if was removed correctly/false the opposite
     */
    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }

    /**
     * Returns the number of questions
     *
     * @return int number of questions
     */
    public int getNumberOfQuestions() {
        return questions.size();
    }

    /**
     * Returns the question in a position receive from parameter
     *
     * @param pos position to search
     * @return Question question in that position/null if position was not valid
     */
    public Question getQuestion(int pos) {
        if (pos >= 0 && pos < questions.size()) {
            return questions.get(pos);
        }
        return null;
    }

    /**
     * Returns the quiz category
     *
     * @return String quiz categoy
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the quiz name
     *
     * @return String quiz categoy
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the group of question
     *
     * @return group of question
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Returns all the questions title non repetead
     *
     * @return questions title
     */
    public Set<String> getQuestionsTitle() {
        Set<String> set = new HashSet<>();
        for (Question q : questions) {
            set.add(q.getTitle());
        }
        return set;
    }

    /**
     * Returns all the questions
     *
     * @return group of questions
     */
    public List<Answer> getAnswers() {
        List<Answer> answers = new ArrayList<>();
        for (Question a : questions) {
            answers.addAll((Collection<? extends Answer>) a.getAnswers());
        }
        return answers;
    }

    /**
     * Verify if a name is contained in the quiz name
     *
     * @param name name to verify
     * @return true if is contained/false the opposite
     */
    public boolean containsName(String name) {
        return this.name.toLowerCase().contains(name.toLowerCase());
    }

    /**
     * Verify if a category is contained in the quiz category
     *
     * @param category category to verify
     * @return true if is contained/false the opposite
     */
    public boolean containsCategory(String category) {
        return this.category.toLowerCase().contains(category.toLowerCase());
    }

    /**
     * Verify if a category and name is contained in the quiz category and name
     *
     * @param info information to verify
     * @return true if is contained/false the opposite
     */
    public boolean containsAll(String info) {
        return (this.category.toLowerCase() + " - " + this.name.toLowerCase()).contains(info.toLowerCase());
    }

    /**
     * Returns a string representation of this answer.
     *
     * @return information
     */
    @Override
    public String toString() {
        return category + " - " + name;
    }

}
