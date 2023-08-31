package org.example.UserInterface;

import org.example.Classes.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAO implements DataBaseUserOperations{
    //Метод за регистриране на потребител.
    @Override
    public void regUser(String userName, String password, BigDecimal balance){

        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String insertQuery = "INSERT INTO users (username, password, balance) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setBigDecimal(3, balance);
            preparedStatement.executeUpdate();
            System.out.println("Registration successful");
        }catch (SQLException e){
            System.out.println("Something went wrong");
            throw new RuntimeException(e);
        }

    }
    @Override
    public User logUser(String userName, String password){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                String userUsername = resultSet.getString("username");
                String userPassword = resultSet.getString("password");
                BigDecimal userBalance = resultSet.getBigDecimal("balance");
                User user = new User(userUsername, userPassword, userBalance);
                return user;
            }
            return null;

        }catch (SQLException e){
            System.out.println("Something went wrong");
            throw new RuntimeException(e);
        }

    }
    @Override
    public void changePassword(String userName, String password, String newPassword){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String updateQuery = "UPDATE users SET password  = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Something went wrong");
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean checkIfUserNameExists(String username){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String selectQuery = "SELECT * FROM users WHERE LOWER(username) = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username.toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean verifyPassword(String password) {

        Pattern pattern1 = Pattern.compile("[\\d+]");
        Pattern pattern2 = Pattern.compile("[a-z]||[A-Z]");
        Pattern pattern3 = Pattern.compile("[^\\w+]");

        Matcher matcher = pattern1.matcher(password);
        boolean first = matcher.find();
        //System.out.println(first);
        matcher = pattern2.matcher(password);
        boolean second = matcher.find();
        //System.out.println(second);
        matcher = pattern3.matcher(password);
        boolean third = matcher.find();
       // System.out.println(third);

        if (first && second && third && password.length() >= 8) {
            return true;
        } else {
            return false;

        }
    }




}
