package org.example.UserInterface;

import org.example.BalanceInterface.BalanceDAO;
import org.example.ClassConfiguration.ApplicationConfig;
import org.example.Classes.Book;
import org.example.Classes.User;

import java.math.BigDecimal;

public class UserService {

    private final BalanceDAO balanceDAO;
    private final UserDAO userDAO;

    public UserService(){
        this.balanceDAO = ApplicationConfig.createBalanceDAO();
        this.userDAO = ApplicationConfig.createUserDAO();
    }

    public void regUser(String userName, String password, BigDecimal balance){
        userDAO.regUser(userName, password, balance);
    }

    public User logUser(String userName, String password){
        return userDAO.logUser(userName, password);
    }

    public void changePassword(String userName, String password, String newPassword){
        userDAO.changePassword(userName, password, newPassword);
    }

    public boolean checkPass(String password){
        return userDAO.verifyPassword(password);
    }

    public boolean checkIfUsernameExists(String userName){
        return userDAO.checkIfUserNameExists(userName);
    }

    public void deposit(String userName , BigDecimal deposit){
        balanceDAO.deposit(userName, deposit);
    }

    public void getBalance(String userName){
        balanceDAO.getBalance(userName);
    }

    public void updateBalance(String userName, BigDecimal newBalance){
        balanceDAO.updateBalance(userName, newBalance);
    }

    public boolean checkIfHaveEnoughBalance(Book book , User user){
        return balanceDAO.checkIfHaveEnoughBalance(book, user);
    }


}
