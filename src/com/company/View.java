package com.company;

import com.company.Utilities.MiscUtility;

public class View {
    private static View instance = null;

    private View(){

    }
    public <T extends DefineMenu> T showMenuAndGetChoice(T[] menuItems){
        do {
            System.out.println("Make a choice:\n---------");
            int i = 1;
            for (T menuItem : menuItems) {
                System.out.println(i + " " + menuItem.getDescription());
                i++;
            }
            int choiceIndex = (MiscUtility.returnIntegerFromString() - 1);
            if (choiceIndex < menuItems.length) {
                return menuItems[choiceIndex];
            }
        }while (true);
    }

    public void showErrorMessage(String errorMessage){
        System.out.println("ERROR! " + errorMessage);
    }

    public static View getInstance() {
        if (instance == null){
            instance = new View();
        }
        return instance;
    }
}
