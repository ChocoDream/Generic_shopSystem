package com.company;

public class StaffManagement {
    private static ProductManagement productManagement;
    private static SaleManagement saleManagement;

    public static void createProduct(){
        productManagement.createProduct();
        saleManagement.setProducts(productManagement.getProducts());
    }

    public static void removeProduct(){
        
    }

    public static void hireStaff(){

    }

    public static void fireStaff(){

    }
}
