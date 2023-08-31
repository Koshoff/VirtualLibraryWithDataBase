package org.example.Classes;

import java.math.BigDecimal;

public class Book {

    private String title;
    private String author;
    private String serialNumber;
    private BigDecimal priceForRent;
    private boolean isRented;


    public Book(String title, String author, String serialNumber, BigDecimal priceForRent) {
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.priceForRent = priceForRent;


    }

    public String getTitle() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPriceForRent() {
        return priceForRent;
    }


    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
