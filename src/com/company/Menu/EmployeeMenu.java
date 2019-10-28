package com.company.Menu;

public enum EmployeeMenu implements DefineMenu {
    ADD_PRODUCT("Add product"),
    REMOVE_PRODUCT("Remove product"),
    SHOW_EMPLOYEES("Show all employees"),
    SHOW_INFO("Show info and check Salary"),
    SAVE_TXTFILE("Save Products as .txt file"),
    LOG_OUT("Log out"),
    ;

    String description;

    private EmployeeMenu(String description){
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
