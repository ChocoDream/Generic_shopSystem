package com.company.Menu;

public enum MainMenu implements DefineMenu {
    GOTO_CUSTOMER("Go to Customer menu"),
    GOTO_EMPLOYEE("Go to Employee menu"),
    GOTO_EMPLOYER("Go to Employer menu"),
    EXIT("Exit Program"),
    ;

    String description;

    private MainMenu(String description){
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
