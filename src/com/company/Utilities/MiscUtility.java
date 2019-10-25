package com.company.Utilities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MiscUtility {
    public static Random rnd = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static int generateID(int length){
        String str = ""; //length tells how long the ID should be. etc 4 numbers long equals length = 4
        for (int i = 0; i < length; i++){
            str += rnd.nextInt(10);
        }
        return Integer.parseInt(str);
    }
    public static int returnIntegerFromString(){
        String str;
        do {
            str = scanner.nextLine();
            try {
                return Integer.parseInt(str);
            } catch (Exception ignored) {
                System.out.println("ERROR: Not a valid Integer");
            }
        }while (true);
    }

    public static float returnFloatFromString(){
        String str;
        do {
            str = scanner.nextLine();
            try {
                return Float.parseFloat(str);
            } catch (Exception ignored) {
                System.out.println("ERROR: Not a valid float");
            }
        }while (true);
    }
}
