package com.company.Menu;

public enum EmployerMenu implements DefineMenu {
    ADD_PRODUCT("Add product"),
    REMOVE_PRODUCT("Show all products and remove products if desired"),
    HIRE_EMPLOYEE("Hire a new employee"),
    FIRE_EMPLOYEE("Fire an employee"),
    SHOW_EMPLOYEES("Show all employees"),
    SHOW_CUSTOMERS("Show all customers"),
    SHOW_INFO("Show info and check Salary"),
    SAVE_TXTFILE("Save Products as .txt file"),
    SAVE_FILE("Save staff as .ser file"),
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