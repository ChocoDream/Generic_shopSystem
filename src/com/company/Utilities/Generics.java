package com.company.Utilities;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Hansson
 * @since 2019-10-30
 *
 * Generic class used in add/remove/show arraylist related classes and save/load files.
 */
public class Generics {
    /**
     * Shows all elements in a list
     *
     * @param list indata ArrayList
     * @param <E> Generic E for element. Could be any type
     */
    public static  <E> void showElementsInArrayList(ArrayList<E> list){
        for(E element : list){ //Generic foreach print element in ArrayList
            System.out.print(element);
        }
        System.out.println();
    }

    /**
     *  Shows all element in a list with an index, starting from 1.
     * @param list indata ArrayList
     * @param <E> Generic E for element. Could be any type
     */
    public static  <E> void showElementsInArrayListWithIndex(ArrayList<E> list){
        int index = 1;
        for(E element : list){ //Generic foreach print element with index in ArrayList
            System.out.printf("%1$d.\t%2$s\n", index++, element);
        }
        System.out.println();
    }

    /**
     *
     * @param list indata ArrayList
     * @param <E> Generic E for element. Could be any tpe
     * @param element element you want to add to ArrayList. Could be any type
     * @return returns the list with the new element added if the element is not empty.
     */
    public static <E> ArrayList<E> addElementToList(ArrayList<E> list, E element){
        if (element != null){
            list.add(element);   //Add element to list if element ain't empty, else return same list without a new element added.
        }
        return list;
    }

    /**
     *
     * @param list indata ArrayList
     * @param index Index of the element you want to remove
     * @param <E> Generic E for element. Could be any type
     * @return returns the list with element removed.
     */
    public static <E> ArrayList<E> removeElementInList(ArrayList<E> list, int index){
        try {
            list.remove(index);
        }
        catch (Exception ignored){

        }
        return list;
    }

    public static <E> void saveFile(ArrayList<E> list, String path, StandardOpenOption... option) {
        if (!list.isEmpty()) {
            if (FileUtility.fileExists(path)) {
                FileUtility.saveObjects(list, path, option);
            } else {
                System.out.println("Creating new files");
                FileUtility.saveObjects(list, path, StandardOpenOption.CREATE);
            }
        }
    }

    /**
     *
     * @param list indata ArrayList
     * @param path path of file-directory
     * @param option StandardOpenOptions, can add multiple
     */
    public static void saveTextFile(List<String> list, String path, StandardOpenOption... option){
        if (FileUtility.fileExists(path)){
            FileUtility.saveText(list, path, option);
        }
        else {
            System.out.println("Creating new files");
            FileUtility.saveText(list, path, StandardOpenOption.CREATE);
        }
    }

    /**
     *
     * @param list indata ArrayList
     * @param path path of file-directory
     * @param <E> Generic E for element. Could be any type
     * @return returns outdata Arraylist. If path found, return list from file, otherwise return indata Arraylist.
     */
    public static <E> ArrayList<E> loadFile(ArrayList<E> list, String path){
        if (FileUtility.fileExists(path)) {
            list = (ArrayList<E>) FileUtility.loadObject(path);
        }
        else {
            System.out.println("No file found");
        }
        return list;
    }
}
