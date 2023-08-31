package org.example.AdminInterface;

import org.example.ClassConfiguration.ApplicationConfig;
import org.example.Classes.Book;

public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(){
        this.adminDAO = ApplicationConfig.createAdminDAO();
    }
    public boolean logAdmin(String userName, String password){
        return adminDAO.logAdmin(userName, password);
    }
    public void delete(String serialNumber){
        adminDAO.delete(serialNumber);
    }
    public void changeContent(String serialNumber, String fieldToChange, String newValue){
        adminDAO.changeContent(serialNumber, fieldToChange, newValue);
    }
    public void saveBook(Book book){
        adminDAO.saveBook(book);
    }
}
