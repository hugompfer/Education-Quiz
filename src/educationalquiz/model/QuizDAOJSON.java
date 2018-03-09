/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * QuizDAOJSON represents a persistance of the type json of quizzes
 *
 * @author hugob
 */
public class QuizDAOJSON implements QuizDAO {

    private final String basePath;
    private List<Quiz> quizs;
    private final static String filename = "quizzes.dat";

    public QuizDAOJSON(String basePath) {
        this.basePath = basePath;
        quizs = new ArrayList<>();
        loadAll();
    }

    //load the file
    private void loadAll() {

        try {
            Gson gso = new GsonBuilder().setLenient().create();
            Gson gso2 = new GsonBuilder().setLenient().create();
            BufferedReader br = new BufferedReader(new FileReader(basePath + filename));

            List<Quiz> list = gso.fromJson(br, new TypeToken<List<Quiz>>() {
            }.getType());

            this.quizs.addAll(list);

        } catch (IOException i) {
            this.quizs = new ArrayList<>();
        }
    }

    //save on file
    private void saveAll() {
        FileWriter writer = null;
        FileWriter writer2 = null;
        try {
            Gson gson = new Gson();
            writer = new FileWriter(basePath + filename);
            gson.toJson(quizs, writer);

            writer.close();
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
