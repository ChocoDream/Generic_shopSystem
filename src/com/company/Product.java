package com.company;

import java.io.Serializable;

public class Product implements Serializable {

    protected enum Type{
        AXES,
        SWORDS,
        ARMOR,
    }

    private String name;
    private float price;
    private Type type;

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
                "\nType: %3$s", name, price, type);
    }
}
