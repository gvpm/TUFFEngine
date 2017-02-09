/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author gvpm
 */
public class Logger {
    String fileName;
    FileWriter arq = null;
    PrintWriter gravarArq;

    public Logger(String fileName) {
        this.fileName = fileName;
        
        
         try {
            arq = new FileWriter(fileName+".txt");

        } catch (IOException ex) {


        }
        gravarArq = new PrintWriter(arq);
        
    }
    
    public void logALine(float flow, float density){
                 gravarArq.println(flow+" "+density);
                 gravarArq.flush();
        
    }
    
    public void closeLogger(){
        gravarArq.close();
        
    }
    
    
    
            
    
}
