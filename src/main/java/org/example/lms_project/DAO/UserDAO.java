package org.example.lms_project.DAO;

import org.example.lms_project.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    protected Connection connectToDatabase(){
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

    public void insertUser(User user) {
        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, email, role) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void addBorrower(User user) {
//        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, email, role) VALUES (?, ?, ?)")) {
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getEmail());
//            preparedStatement.setString(3, user.getRole());
//            preparedStatement.executeUpdate();
//            System.out.println(preparedStatement);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public User getUserById(Long id) {
        User user =null;

        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, email, role FROM user WHERE id = ?;")) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                String name = result.getString("name");
                String email = result.getString("email");
                String role = result.getString("role");

                user = new User(id, name, email, role );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT id, name, email, role FROM user")) {

            while (result.next()) {
                Long id = result.getLong("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String role = result.getString("role");

                User user = new User(id, name, email, role);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public boolean updateUser(User user) {
        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET name = ?, email = ?, role = ? WHERE id = ?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setLong(4, user.getId());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

