package org.example.lms_project.test.DAO;


import org.example.lms_project.DAO.UserDAO;
import org.example.lms_project.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UserDAOTest {

    @InjectMocks
    private UserDAO userDAO;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        userDAO = new UserDAO(){
            @Override
            protected Connection connectToDatabase() {
                return connection;
            }
        };
    }

    @Test
    void testInsertUser() throws SQLException {
        // Arrange
        User user = new User(1L, "John Doe", "john@example.com", "Admin");

        // Act
        userDAO.insertUser(user);

        // Assert
        verify(preparedStatement).setString(1, user.getName());
        verify(preparedStatement).setString(2, user.getEmail());
        verify(preparedStatement).setString(3, user.getRole());
        verify(preparedStatement).executeUpdate();
    }


    @Test
    void testGetUserById() throws SQLException {
        // Arrange
        User expectedUser = new User(1L, "John Doe", "john@example.com", "Admin");
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getLong("id")).thenReturn(expectedUser.getId());
        when(resultSet.getString("name")).thenReturn(expectedUser.getName());
        when(resultSet.getString("email")).thenReturn(expectedUser.getEmail());
        when(resultSet.getString("role")).thenReturn(expectedUser.getRole());

        // Act
        User actualUser = userDAO.getUserById(1L);

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getRole(), actualUser.getRole());
    }

    @Test
    void testGetAllUsers() throws SQLException {
        // Arrange
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any(String.class))).thenReturn(resultSet);
        User user = new User(1L, "John Doe", "john@example.com", "Admin");
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getLong("id")).thenReturn(user.getId());
        when(resultSet.getString("name")).thenReturn(user.getName());
        when(resultSet.getString("email")).thenReturn(user.getEmail());
        when(resultSet.getString("role")).thenReturn(user.getRole());
        // Act
        List<User> users = userDAO.getAllUsers();

        // Assert
        assertNotNull(users);
        assertEquals(1, users.size());
        User actualUser = users.get(0);
        assertEquals(user.getId(), actualUser.getId());
        assertEquals(user.getName(), actualUser.getName());
        assertEquals(user.getEmail(), actualUser.getEmail());
        assertEquals(user.getRole(), actualUser.getRole());
    }
}

