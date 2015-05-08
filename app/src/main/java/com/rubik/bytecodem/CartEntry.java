package com.rubik.bytecodem;

/**
 * Created by Mehedee Zaman on 5/8/2015.
 */
public class CartEntry {

    public Product product;
    public int quantity;
    public double subTotal;
    public String orderID;

    public CartEntry(Product product, int quantity, double subTotal, String orderID) {
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.orderID = orderID;
    }
}
