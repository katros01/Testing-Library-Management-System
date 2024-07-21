package org.example.lms_project.test.integration;


import org.example.lms_project.DAO.UserDAO;
import org.example.lms_project.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UpdateUserTest {
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UpdateUserTest updateUserTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateUser() {
        User user = new User(1L, "John Doe", "john@example.com", "member");

        when(userDAO.updateUser(user)).thenReturn(true);

        boolean result = userDAO.updateUser(user);

        verify(userDAO, times(1)).updateUser(user);
        assert(result);
    }
}
