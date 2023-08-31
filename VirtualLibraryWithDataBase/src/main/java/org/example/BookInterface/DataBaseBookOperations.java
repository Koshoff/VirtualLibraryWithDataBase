package org.example.BookInterface;

import org.example.Classes.Book;

public interface DataBaseBookOperations {

   // void save(String serialNumber, String column, String value, BigDecimal priceForRent);
    boolean exists(String serialNumber);
    void rentBook(String title, String serialNumber);
    void returnBook(String title, String serialNumber);

    Book select(String title);




}
