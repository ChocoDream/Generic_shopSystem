package com.company.Menu;

public enum EmployerMenu implements DefineMenu {
    ADD_PRODUCT("Add product"),
    HIRE_EMPLOYEE("Hire a new employee"),
    SHOW_EMPLOYEES("Show all employees"),
    SHOW_CUSTOMERS("Show all customers"),
    SHOW_INFO("Show info and check Salary"),
    SAVE_FILE("Save Files"),
    LOAD_FILE("Load Files"),
    LOG_OUT("Log out"),
    ;

    String description;

    private EmployerMenu(String description){
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
