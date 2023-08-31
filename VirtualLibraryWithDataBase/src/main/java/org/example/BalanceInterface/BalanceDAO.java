package org.example.BalanceInterface;

import org.example.BalanceInterface.DataBaseBalanceOperations;
import org.example.Classes.Book;
import org.example.Classes.User;

import java.math.BigDecimal;
import java.sql.*;

public class BalanceDAO implements DataBaseBalanceOperations {


    // Метод за добавяне на сума към баланса на потребител
    @Override
    public void deposit(String userName , BigDecimal deposit){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String updateQuery = "UPDATE users SET balance = balance + ? WHERE username = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setBigDecimal(1, deposit);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //Метод за извличане на баланс от база данни за конкретен потребител.
    @Override
    public BigDecimal getBalance(String userName){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String selectQuery = "SELECT * FROM books WHERE username = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getBigDecimal("balance");
            }
            return BigDecimal.ZERO;


        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //Метод за обновяване на баланс за конкретен потребител.
    @Override
    public void updateBalance(String userName, BigDecimal newBalance){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String updateQuery = "UPDATE users SET balance = ? WHERE username = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    public boolean checkIfHaveEnoughBalance(Book book , User user){
        return (user.getBalance().subtract(book.getPriceForRent())).intValue() >= 0;

    }

}
