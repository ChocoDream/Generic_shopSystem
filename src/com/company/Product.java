package com.company;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {

    protected enum Type{
        AXES,
        SWORDS,
        ARMOR,
    }

    protected enum SortBy{
        HIGHESTPRICE("Highest Price"),
        LOWESTPRICE("Lowest Price"),
        ;

        String description;
        SortBy(String description){
            this.description = description;
        }
    }

    private String name;
    private float price;
    private Type type;

    private static SortBy sortBy = SortBy.HIGHESTPRICE;

    Product(String name, float price, Type type){
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }

    public String toString() {
        return String.format("%1$s\t%2$.2f SEK" +
                "\nType: %3$s\n", name, price, type);
    }

    public static void setSortBy(int enumIndex) {
        switch (enumIndex){
            case 0:
                sortBy = SortBy.HIGHESTPRICE;
                break;
            case 1:
                sortBy = SortBy.LOWESTPRICE;
                break;
            default:
        }
    }

    public static SortBy getSortBy() {
        return sortBy;
    }

    public int compareTo(Product otherProduct){
        switch (sortBy){
            case HIGHESTPRICE:
                return -(int)(getPrice() - otherProduct.getPrice());
            case LOWESTPRICE:
                return (int)(getPrice() - otherProduct.getPrice());
            default:
                View.getInstance().showErrorMessage(sortBy + " Product.sortBy");
                return 0;
        }
    }
}
