package org.example.lms_project.test.Model;

import org.example.lms_project.Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testConstructorWithId() {
        User user = new User(111L, "John Doe", "john.doe@example.com", "Admin");

        assertEquals(111L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("Admin", user.getRole());
    }

    @Test
    public void testConstructorWithoutId() {
        User user = new User("Jane Doe", "jane.doe@example.com", "User");

        assertEquals(null, user.getId());
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("User", user.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User("Initial Name", "initial.email@example.com", "Initial Role");

        user.setId(112L);
        user.setName("Updated Name");
        user.setEmail("updated.email@example.com");
        user.setRole("Updated Role");

        assertEquals(112L, user.getId());
        assertEquals("Updated Name", user.getName());
        assertEquals("updated.email@example.com", user.getEmail());
        assertEquals("Updated Role", user.getRole());
    }
}

