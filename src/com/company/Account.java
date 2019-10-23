package com.company;

import java.io.Serializable;

public abstract class Account implements Serializable {
    protected String name;

    Account(String name){
        this.name = name;
    }

    public abstract void showInfo();

    public abstract String toString();
}
