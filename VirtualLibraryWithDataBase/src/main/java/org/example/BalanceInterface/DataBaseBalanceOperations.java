package org.example.BalanceInterface;

import java.math.BigDecimal;

public interface DataBaseBalanceOperations {

    void deposit(String userName , BigDecimal deposit);
    BigDecimal getBalance(String userName);
    void updateBalance(String userName, BigDecimal newBalance);

}
