package com.company;

import com.company.Utilities.MiscUtility;

import java.io.Serializable;

public abstract class Account implements Serializable {
    protected String name;
    protected int ID;

    Account(String name){
        this.name = name;
        generateID();
    }

    public abstract void showInfo();

    public abstract String toString();

    public int getID() {
        return ID;
    }

    private void generateID(){
        this.ID = MiscUtility.generateID(4); //Length of ID
    }
}
