package org.example.AdminInterface;

import org.example.Classes.Book;

public interface DataBaseAdminOperations {

    boolean logAdmin(String userName, String password);
    void delete(String serialNumber);
    void changeContent(String serialNumber, String fieldToChange, String newValue);
    void saveBook(Book book);
}
