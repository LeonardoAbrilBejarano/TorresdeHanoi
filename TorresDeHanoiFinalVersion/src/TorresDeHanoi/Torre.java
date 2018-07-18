/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TorresDeHanoi;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author leillo
 */
public class Torre {
    private ArrayList torre;       

    public Torre() {
        torre=new ArrayList(); 
    }
    
    public void addDisco(Disco d){
        torre.add(d);
    }
    
    public void removeDisco(){
        torre.remove(torre.size()-1);
    }
    
    public Disco showTopDisco(){
        return (Disco) torre.get(torre.size()-1);
    }
       
    public ArrayList getTorre() {
        return torre;
    }

    public void setTorre(ArrayList torre) {
        this.torre = torre;
    }


}
