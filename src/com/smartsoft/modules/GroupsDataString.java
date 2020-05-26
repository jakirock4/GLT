/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.modules;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class GroupsDataString {
    
    private int ID;
    private String CurrentVector;
    private String Groups;
    private String NeighborVectosString;
    private String FilteredVectosString; 

    public int getID() {
        return ID;
    }

    public String getCurrentVector() {
        return CurrentVector;
    }

    public String getGroups() {
        return Groups;
    }

    public String getNeighborVectosString() {
        return NeighborVectosString;
    }

    public String getFilteredVectosString() {
        return FilteredVectosString;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCurrentVector(String CurrentVector) {
        this.CurrentVector = CurrentVector;
    }

    public void setGroups(String Groups) {
        this.Groups = Groups;
    }

    public void setNeighborVectosString(String NeighborVectosString) {
        this.NeighborVectosString = NeighborVectosString;
    }

    public void setFilteredVectosString(String FilteredVectosString) {
        this.FilteredVectosString = FilteredVectosString;
    }
    
}
