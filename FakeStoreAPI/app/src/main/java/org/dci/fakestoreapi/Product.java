package org.dci.fakestoreapi;

import android.widget.ImageView;

public class Product {
    private final int id;
    private final String title;
    private final double price;
    private final String description;
    private final String category;
    private final String image;
    private final double rate;

    public Product(int id, String title, double price, String description, String category, String image, double rate) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rate = rate;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public double getRate() {
        return rate;
    }

}
