package org.example.ClassConfiguration;

import org.example.AdminInterface.AdminDAO;
import org.example.AdminInterface.AdminService;
import org.example.BookInterface.BookDAO;
import org.example.BookInterface.BookService;
import org.example.BalanceInterface.BalanceDAO;
import org.example.UserInterface.UserDAO;
import org.example.UserInterface.UserService;

public class ApplicationConfig {

    public static BookService createBookService() {
        return new BookService();
    }

    public static UserService createUserService() {
        return new UserService();
    }

    public static AdminService createAdminService() {
        return new AdminService();
    }


    public static UserDAO createUserDAO(){
        return new UserDAO();
    }

    public static BalanceDAO createBalanceDAO(){
        return new BalanceDAO();
    }

    public static AdminDAO createAdminDAO(){
        return new AdminDAO();
    }

    public static BookDAO createBookDAO(){
        return new BookDAO();
    }


}
