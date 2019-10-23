package com.company.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Generics {
    public static  <E> void showElementsInArrayList(ArrayList<E> list){
        for(E element : list){ //Generic foreach print element in ArrayList
            System.out.print(element);
        }
        System.out.println();
    }
}
