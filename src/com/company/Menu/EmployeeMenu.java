package com.company.Menu;

import com.company.DefineMenu;

public enum EmployeeMenu implements DefineMenu {
    ADD_PRODUCT("Add product"),
    SHOW_EMPLOYEES("Show all employees"),
    SHOW_INFO("Show info and check Salary"),
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
