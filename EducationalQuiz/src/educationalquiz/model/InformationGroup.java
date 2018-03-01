/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package educationalquiz.model;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author hugob
 */
public class InformationGroup implements Serializable{
    private Map<String,Boolean> informations;
    
    public InformationGroup(){
        informations=new HashMap<>();
    }
    
    public void addQuestion(String info,boolean isQuestion){
        this.informations.put(info,isQuestion);
    }
    
    public void removeQuestion(String info){
        this.informations.remove(info);
    }
    
    public void updateQuestion(String info,String newInfo,boolean isQuestion){
        this.informations.remove(info);
        this.informations.put(newInfo,isQuestion);
    }
    
}
