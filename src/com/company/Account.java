package com.company;

import com.company.Utilities.MiscUtility;

import java.io.Serializable;

public abstract class Account implements Serializable {
    protected String name;
    protected int ID;

    Account(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public abstract void showInfo();

    public abstract String toString();
}
