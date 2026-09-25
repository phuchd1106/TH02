package com.example.th02.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Food implements Serializable {
    private final int id;
    private final String name;
    private final String description;
    private final double price;
    private final int imageResId;
    private final String ingredients;

    public Food(int id, String name, String description, double price, int imageResId, String ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getFormattedPrice() {
        NumberFormat formatter = NumberFormat.getInstance(Locale.forLanguageTag("vi-VN"));
        return formatter.format((long) price) + " VNĐ";
    }

    public String getFormattedTotalPrice(int quantity) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.forLanguageTag("vi-VN"));
        return formatter.format((long) (price * quantity)) + " VNĐ";
    }
}
