package com.sangptit.nguyenluutansang_n22dccn068.models;

public class Book {
    private String title;
    private String author;
    private String price;
    private int imageResId;

    public Book(String title, String author, String price, int imageResId) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
