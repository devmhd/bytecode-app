package com.rubik.bytecodem;

/**
 * Created by Mehedee Zaman on 5/8/2015.
 */
public class Product {

    public String title;
    public String description;
    public String id;
    public double price;
    public String thumbUrl;

    public Product(String title, String description, String id, double price, String thumbUrl) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.price = price;
        this.thumbUrl = thumbUrl;
    }
}
