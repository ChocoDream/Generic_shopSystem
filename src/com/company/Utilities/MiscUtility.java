package com.company.Utilities;

import java.util.Random;
import java.util.Scanner;

public class MiscUtility {
    public static Random rnd = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static int generateRandomID(int length){
        String str = "";
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

            }
        }while (true);
    }
}
