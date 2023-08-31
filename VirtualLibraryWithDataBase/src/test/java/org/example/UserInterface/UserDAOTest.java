package org.example.UserInterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class UserDAOTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    private UserDAO userDAO;


    @BeforeEach
    public void setup() throws SQLException {
        userDAO = new UserDAO();
        mockConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtuallibrarytestdatabase", "root", "Svetatroica13");
        when(mockConnection.prepareStatement(any())).thenReturn(mockPreparedStatement);
    }

    @Test
    public void testRegUser() throws SQLException{
        String userName = "testuser";
        String password = "testpassword";
        BigDecimal balance = new BigDecimal(100);
        userDAO.regUser(userName, password , balance);
    }

    @Test
    public void logUser() {
    }

    @Test
    public void changePassword() {
    }

    @Test
    public void checkIfUserNameExists() {
    }

    @Test
    public void testVerifyPasswordWithCorrectPassword() {
        String password = "5!svvbcg";
        boolean isValid = userDAO.verifyPassword(password);
        assertTrue(isValid);
    }
    @Test
    public void testVerifyPasswordWithInvalidPassword(){
        //the length of the password is < 8 symbols
        String password = "22211b#";
        boolean isValid = userDAO.verifyPassword(password);
        assertFalse(isValid);
    }
}