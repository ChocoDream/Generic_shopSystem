package com.company;

import com.company.Utilities.Generics;
import com.company.Utilities.MiscUtility;

import java.util.ArrayList;

public class StaffManagement {
    private static ProductManagement productManagement;
    private static SaleManagement saleManagement;

    public static void fireStaff(ArrayList<StaffAccount> staff){
        System.out.println("Who do you want to fire?");
        String name = MiscUtility.scanner.nextLine();

        for (int i = 0; i < staff.size(); i++){
            if (staff.get(i).name.equals(name)){
                System.out.println(staff.get(i).name + " has been removed");
                Generics.removeElementInList(staff, i);
                return;
            }
        }
        System.out.printf("%s does not exist\n", name);
    }

    public static void showList(ArrayList<StaffAccount> staff){
        Generics.showElementsInArrayListWithIndex(staff);
    }
}
