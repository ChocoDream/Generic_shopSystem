package com.company.Menu;

public enum CustomerMenu implements DefineMenu {
    GO_TO_STORE("Go to Store"),
    SHOW_CART("Show shopping cart"),
    LOG_OUT("Log out"),
    ;

    String description;

    private CustomerMenu(String description){
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
