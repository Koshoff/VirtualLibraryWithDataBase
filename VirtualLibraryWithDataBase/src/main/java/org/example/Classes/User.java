package org.example.Classes;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private String userName;
    private String password;
    private BigDecimal balance;

    public User(String userName, String password, BigDecimal balance) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
