package com.company.Utilities;

import com.company.Blacksmith;

import java.nio.file.StandardOpenOption;
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
    public static  <E> void showElementsInArrayListWithIndex(ArrayList<E> list){
        int index = 1;
        for(E element : list){ //Generic foreach print element with index in ArrayList
            System.out.printf("%1$d.\t%2$s\n", index++, element);
        }
        System.out.println();
    }

    public static <E> ArrayList<E> addElementToList(ArrayList<E> list, E element){
        if (element != null){
            list.add(element);   //Add element to list if element ain't empty, else return same list without a new element added.
        }
        return list;
    }

    public static <E> void saveFile(ArrayList<E> list, String path){
        if (!list.isEmpty()) {
            if (FileUtility.fileExists(path)) {
                FileUtility.saveObject(list, path, StandardOpenOption.APPEND);
            } else {
                System.out.println("Creating new files");
                FileUtility.saveObject(list, path, StandardOpenOption.CREATE);
            }
        }
    }

    public static <E> ArrayList<E> loadFile(ArrayList<E> list, String path){
        if(FileUtility.fileExists(path)){
            list = (ArrayList<E>)FileUtility.loadObject(path);
        }
        else {
            System.out.println("No file found");
        }
        return list;
    }
}
