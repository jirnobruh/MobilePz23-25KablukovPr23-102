package com.example.pr23kablukovpr23_102;

public class AnalysisModel {
    private String title;
    private String days;
    private String price;
    private String category;

    public AnalysisModel(String title, String days, String price, String category) {
        this.title = title;
        this.days = days;
        this.price = price;
        this.category = category;
    }

    public String getTitle() { return title; }
    public String getDays() { return days; }
    public String getPrice() { return price; }
    public String getCategory() { return category; }
}