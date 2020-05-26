/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.mobileObject;

import java.util.ArrayList;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class LoadedDistrubtions {
    
    private ArrayList<DurationPath> Pathes;

    public ArrayList<DurationPath> getPathes() {
        return this.Pathes;
    }
    /**
     * 
     */
    public void LoadedDistrubtions(){
    
        this.Pathes = new ArrayList<>();
        
        DurationPath x0 = new DurationPath();
        DurationPath x1 = new DurationPath();
        DurationPath x2 = new DurationPath();
        DurationPath x3 = new DurationPath();
        DurationPath x4 = new DurationPath();
        DurationPath x5 = new DurationPath();
        DurationPath x6 = new DurationPath();
        DurationPath x7 = new DurationPath();
        DurationPath x8 = new DurationPath();
        x0.setID(0);
        x0.setStart(0);
        x0.setEnd(2);
        x1.setID(1);
        x1.setStart(3);
        x1.setEnd(4);
        x2.setID(2);
        x2.setStart(5);
        x2.setEnd(6);
        x3.setID(3);
        x3.setStart(7);
        x3.setEnd(8);
        x4.setID(4);
        x4.setStart(9);
        x4.setEnd(80);
        x5.setID(5);
        x5.setStart(81);
        x5.setEnd(82);
        x6.setID(6);
        x6.setStart(83);
        x6.setEnd(85);
        x7.setID(7);
        x7.setStart(86);
        x7.setEnd(88);
        x8.setID(8);
        x8.setStart(89);
        x8.setEnd(90);
        Pathes.add(x0);
        Pathes.add(x1);
        Pathes.add(x2);
        Pathes.add(x3);
        Pathes.add(x4);
        Pathes.add(x5);
        Pathes.add(x6);
        Pathes.add(x7);
        Pathes.add(x8);   
    }
    
}
