package com.company;

public abstract class StaffAccount extends Account{
    protected int salary;

    StaffAccount(String name, int salary){
        super(name);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Salary: %d\n", salary);
    }
}
