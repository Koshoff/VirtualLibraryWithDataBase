package org.example.BookInterface;

import org.example.ClassConfiguration.ApplicationConfig;
import org.example.BalanceInterface.BalanceDAO;
import org.example.Classes.Book;

import java.math.BigDecimal;

public class BookService {
    private final BookDAO bookDAO;
    private final BalanceDAO balanceDAO;

    public BookService(){
        bookDAO = ApplicationConfig.createBookDAO();
        balanceDAO = ApplicationConfig.createBalanceDAO();
    }

    public boolean exists(String serialNumber){
        return bookDAO.exists(serialNumber);
    }

    public void rentBook(String userName, String serialNumber){
       bookDAO.rentBook(userName, serialNumber);
    }
    public void returnBook(String userName, String serialNumber){
        bookDAO.returnBook(userName, serialNumber);
    }

    public Book select(String title){
        return bookDAO.select(title);
    }


}
