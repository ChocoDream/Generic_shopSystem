package com.company;

public abstract class StaffAccount extends Account{
    protected int salary;

    StaffAccount(String name, int salary, int ID){
        super(name, ID);
        this.salary = salary;
    }

    public void showInfo() {
        System.out.printf("Showing info for %1$s\n" +
                "\tAccountType : %4$s\n" +
                "\tname : %1$s\n" +
                "\tID : %2$d\n" +
                "\tSalary : %3$d\n", name, ID, salary, type.toString());
    }
    @Override
    public String toString() {
        return String.format("Name: %1$s | AccountType : %2$s", name, type);
    }
}
