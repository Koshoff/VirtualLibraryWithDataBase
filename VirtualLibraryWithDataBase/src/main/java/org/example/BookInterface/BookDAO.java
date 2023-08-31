package org.example.BookInterface;

import org.example.Classes.Book;

import java.math.BigDecimal;
import java.sql.*;

public class BookDAO implements DataBaseBookOperations {

    @Override
    public boolean exists(String serialNumber){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String selectQuery = "SELECT * FROM books WHERE serialNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, serialNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else{
                return false;
            }

            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    @Override
    public void rentBook(String userName, String serialNumber) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String insertQuery = "INSERT INTO loans (user_username, book_serialNumber) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, serialNumber);
            preparedStatement.executeUpdate();

            String updateBookStatusQuery = "UPDATE books SET isRented = true WHERE serialNumber = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateBookStatusQuery);
            updateStatement.setString(1, serialNumber);
            updateStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void returnBook(String userName, String serialNumber) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){

            String updateQuery = "UPDATE loans SET returned = true WHERE user_username = ? AND book_serialNumber = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, userName);
            updateStatement.setString(2, serialNumber);
            updateStatement.executeUpdate();
            String updateBookStatusQuery = "UPDATE books SET isRented = false WHERE serialNumber = ?";
            PreparedStatement updateStatementBook = connection.prepareStatement(updateBookStatusQuery);
            updateStatementBook.setString(1, serialNumber);
            updateStatementBook.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public BigDecimal getPriceForRent(String title){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String selectQuery = "SELECT * FROM books WHERE title = ?";
            PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getBigDecimal("rentalPrice");
            }

        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public Book select(String title){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String selectQuery = "SELECT * FROM books WHERE title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("title");
                String author = resultSet.getString("author");
                String serialNumber = resultSet.getString("serialNumber");
                BigDecimal rentalPrice = resultSet.getBigDecimal("rentalPrice");

                return new Book(name, author, serialNumber, rentalPrice);
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


