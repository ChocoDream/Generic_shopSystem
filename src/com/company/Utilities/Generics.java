package com.company.Utilities;

import java.util.ArrayList;

public class Generics {
    public static  <E> void showElementsInArrayList(ArrayList<E> list){
        for(E element : list){ //Generic foreach print element in ArrayList
            System.out.println(element);
        }
    }
}
