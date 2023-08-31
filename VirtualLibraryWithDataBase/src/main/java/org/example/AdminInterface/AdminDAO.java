package org.example.AdminInterface;

import org.example.Classes.Book;

import java.math.BigDecimal;
import java.sql.*;

public class AdminDAO implements DataBaseAdminOperations {
    @Override
    public boolean logAdmin(String userName, String password){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")){
            String selectQuery = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    @Override
    public void delete(String serialNumber) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")) {
            String deleteQuery = "DELETE FROM books WHERE serialNumber = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteQuery);
            preparedStatement.setString(1, serialNumber);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public void changeContent(String serialNumber, String fieldToChange, String newValue) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")) {
            String updateQuery = "UPDATE books SET " + fieldToChange + " = ? WHERE serialNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, serialNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveBook(Book book) {
        // Book book = new Book(serialNumber, column, value, priceForRent);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrary", "root", "Svetatroica13")) {
            String insertQuery = "INSERT INTO books (title, author, serialNumber, rentalPrice) VALUES(? , ? , ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getSerialNumber());
            preparedStatement.setBigDecimal(4, book.getPriceForRent());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
