package com.company;

public class Product implements Comparable<Product>{

    protected enum Type{
        AXES,
        SWORDS,
        ARMOR,
    }
    private enum SortBy{
        HIGHESTPRICE,
        LOWESTPRICE,
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

    public String toString() {
        return String.format("%1$s\t%2$f SEK" +
                "\nType: %3$s", name, price, type);
    }

    public int compareTo(Product product){
        switch (sortBy){
            case HIGHESTPRICE:
                return (int)-(getPrice() - product.getPrice());
            case LOWESTPRICE:
                return (int)(getPrice() - product.getPrice());
            default:
                System.out.println("INVALID");
                return 0;
        }
    }
}
