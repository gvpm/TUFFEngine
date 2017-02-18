/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

import java.util.concurrent.Callable;

/**
 *
 * @author gvpm
 */
public class IterationCallable implements Callable<Integer> {

    Core core;
    int partNumber, parts;
    String name;

    public IterationCallable(Core core, int partNumber, int parts, String name) {
        this.core = core;
        this.partNumber = partNumber;
        this.parts = parts;
        this.name = name;
    }



    @Override
    public Integer call() throws Exception {
        //System.out.println("Entered:"+name);
        core.iteratePart(partNumber, parts);
        
        return 1;
    }

}
