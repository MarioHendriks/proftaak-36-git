package com.PTS3S36G3.UserService.DAL.Contexts;

import com.PTS3S36G3.UserService.DAL.Interfaces.Context.IUserContext;
import com.PTS3S36G3.UserService.Models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserContextSQLtest implements IUserContext
{
    private String url = "jdbc:mysql://77.251.225.24/microservicesg3";
    private String username = "root";
    private String password = "OzOc5NHrMoidwD3VkzzY";

    public User GetUserById(int userId)
    {
        User user = new User();

        // create our mysql database connection
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // SQL query calling stored procedure
            String query = "select id, username, email from user_test where id = ?;";

            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query)) {
                cst.setInt(1, userId);
                try (ResultSet rs = cst.executeQuery()) {
                    // iterate through the java resultset
                    while (rs.next()) {
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return user;
    }

    public User GetUserByUsername(String username)
    {
        return null;
    }

    public User GetUserByEmail(String email)
    {
        return null;
    }

    public User AddUser(User user)
    {
        return null;
    }

    public boolean DeleteUser(String name)
    {
        return false;
    }
}
