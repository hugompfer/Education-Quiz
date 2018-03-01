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

    //le o ficheio 
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

    @Override
    public boolean updateQuiz(Quiz lastQuiz, Quiz newQuiz) {
        if (lastQuiz != null && newQuiz != null) {
            if (quizs.remove(lastQuiz)) {
                if (quizs.add(newQuiz)) {
                    saveAll();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Quiz> selectQuiz() {
        return quizs;
    }

    @Override
    public boolean removeQuiz(Quiz quiz) {
        if (quizs.remove(quiz)) {
            saveAll();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateQuiz(Quiz quiz) {
        for(Quiz q:quizs){
            if(quiz==q){
                quizs.set(quizs.indexOf(q), quiz);
                saveAll();
                return true;
            }
        }
        return false;
    }

}
