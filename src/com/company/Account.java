package com.company;

import com.company.Factories.AccountFactory;

import java.io.Serializable;

public abstract class Account implements Serializable {
    protected String name;
    protected int ID;
    protected AccountFactory.AccountType type;

    Account(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public AccountFactory.AccountType getType() {
        return type;
    }

    public abstract void showInfo();

    public abstract String toString();
}
