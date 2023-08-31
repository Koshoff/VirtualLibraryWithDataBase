package org.example.UserInterface;

import org.example.Classes.User;

import java.math.BigDecimal;

public interface DataBaseUserOperations {

    void regUser(String userName, String password, BigDecimal balance);

    User logUser(String userName, String password);
    void changePassword(String userName, String password, String newPassword);
    boolean checkIfUserNameExists(String username);
    boolean verifyPassword(String password);
}
