/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * QuizDAOSerializable represents a persistance of the type serializable of
 * quizzes
 *
 * @author hugob
 */
public class QuizDAOSerializable implements QuizDAO, Serializable {

    private String basePath;
    private List<Quiz> quizs;
    private final static String filename = "quizs.dat";

    public QuizDAOSerializable(String basePath) {
        this.basePath = basePath;
        quizs = new ArrayList<>();
        loadAll();
    }

    //load the file
    private void loadAll() {
        try {
            FileInputStream fileIn = new FileInputStream(this.basePath + filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.quizs = (ArrayList<Quiz>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
        }
    }
    
    //save on file
    private void saveAll() {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(basePath + filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(quizs);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

    }

    /**
     * insert the quiz in the persistance and save
     *
     * @param quiz quiz to add
     * @return true if was a sucess /false the opposite
     */
    @Override
    public boolean insertQuiz(Quiz quiz) {
        if (quiz != null) {
            if (quizs.add(quiz)) {
                saveAll();
                return true;
            }
        }
        return false;
    }

     /**
     * Return the list of quizzes persisted
     *
     * @return list of quizzes 
     */
    @Override
    public List<Quiz> selectQuiz() {
        return quizs;
    }

     /**
     * remove the quiz in the persistance and save
     *
     * @param quiz quiz to remove
     * @return true if was a sucess /false the opposite
     */
    @Override
    public boolean removeQuiz(Quiz quiz) {
        if (quizs.remove(quiz)) {
            saveAll();
            return true;
        }
        return false;
    }

    /**
     * update the quiz in the persistance and save
     *
     * @param quiz quiz to update
     * @param category new category
     * @param name new name
     * @return true if was a sucess /false the opposite
     */
    @Override
    public boolean updateQuiz(Quiz quiz, String category, String name) {
        for (Quiz q : quizs) {
            if (quiz == q) {
                quiz.setCategory(category);
                quiz.setName(name);
                quizs.set(quizs.indexOf(q), quiz);
                saveAll();
                return true;
            }
        }
        return false;
    }

    /**
     * update the quiz in the persistance and save
     *
     * @param quiz quiz to update
     * @return true if was a sucess /false the opposite
     */
    @Override
    public boolean updateQuiz(Quiz quiz) {
        for (Quiz q : quizs) {
            if (quiz == q) {
                quizs.set(quizs.indexOf(q), quiz);
                saveAll();
                return true;
            }
        }
        return false;
    }

}
