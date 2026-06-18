package com.example.pr23kablukovpr23_102;

public class NewsModel {
    private String title;
    private String description;
    private String price;
    private int backgroundColor;

    public NewsModel(String title, String description, String price, int backgroundColor) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.backgroundColor = backgroundColor;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public int getBackgroundColor() { return backgroundColor; }
}