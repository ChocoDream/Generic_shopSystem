package com.company;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class ProductManagementTest {
    private ProductManagement productManagement = new ProductManagement();

    @Test
    public void createProduct() {
        int amountOfProductsInList = productManagement.getProducts().size();
        String input = "SwordTest\n69.69f\nSWORDS\nyes";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        productManagement.createProduct();

        assertEquals(amountOfProductsInList + 1, productManagement.getProducts().size());
    }
}