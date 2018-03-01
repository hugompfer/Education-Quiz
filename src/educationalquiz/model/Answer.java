/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.Serializable;

/**
 *
 * @author hugob
 */
public class Answer implements Serializable{
    private String information;
    private boolean correct;
    
    public Answer(String information,boolean correct){
        this.information=information;
        this.correct=correct;
    }
    
    public String getInformation(){
        return information;
    }
    
    public boolean isCorrect(){
        return correct;
    }
    
    public boolean isTheSame(Answer answer){
        return answer.getInformation().equals(information) && answer.isCorrect()==correct;
    }
    
     public boolean containsInformation(String info){
        return information.contains(info);
    }
    
    @Override
    public String toString(){
        return information;
    }
}
