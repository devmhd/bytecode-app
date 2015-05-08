package com.rubik.bytecodem;

/**
 * Created by Mehedee Zaman on 5/8/2015.
 */
public class CartEntry {

    public Product product;
    public int quantity;

    public CartEntry(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
