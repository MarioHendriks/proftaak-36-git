package com.PTS3S36G3.UserService.DAL.Contexts;

import com.PTS3S36G3.UserService.DAL.Interfaces.Context.IUserContext;
import com.PTS3S36G3.UserService.Models.User;
import java.sql.*;

public class UserContextSQL implements IUserContext
{
    private String url = "jdbc:mysql://77.251.225.24/microservicesg3";
    private String username = "microservicesg3";
    private String password = "OzOc5NHrMoidwD3VkzzY";

    public User GetUserById(int userId)
    {
        User user = new User();

        // create our mysql database connection
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            // SQL query calling stored procedure
            String query = "select id, username, email from user where id = ?;";

            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query))
            {
                cst.setInt(1, userId);

                try (ResultSet rs = cst.executeQuery())
                {
                    // iterate through the java resultset
                    while (rs.next())
                    {
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return user;
    }

    public User GetUserByUsername(String username)
    {
        User user = new User();
        user.setId(0);

        try (Connection conn = DriverManager.getConnection(this.url, this.username, this.password))
        {
            String query = "select id, username, email from user where username = ?;";

            // create the java statement
            try (PreparedStatement cst = conn.prepareCall(query))
            {
                cst.setString(1, username);

                try (ResultSet rs = cst.executeQuery())
                {
                    // iterate through the java resultset
                    while (rs.next())
                    {
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return user;
    }

    public User GetUserByEmail(String email)
    {
        User user = new User();

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            String query = "select id, username, email from user where email = ?;";

            // create the java statement
            try (PreparedStatement cst = conn.prepareCall(query))
            {
                cst.setString(1, email);

                try (ResultSet rs = cst.executeQuery())
                {
                    // iterate through the java resultset
                    while (rs.next())
                    {
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("username"));
                        user.setEmail(rs.getString("email"));
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return user;
    }

    public User AddUser(User user)
    {
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            String query = "{call AddUser(?, ?)}";

            // create the java statement
            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setString(1, user.getName());
                cst.setString(2, user.getEmail());

                try(ResultSet rs = cst.executeQuery())
                {
                    while(rs.next())
                    {
                        user.setId(rs.getInt("id"));
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return user;
    }

    public boolean DeleteUser(String user)
    {
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            String query = "DELETE FROM user WHERE username = 'ewoxRxHtXSTXMMotKBhBqcQjoLQh3P4HM7fMkI3btiPjTVipnnTmwyErJYcbYR0fPbF73iCn9AQAcSq3rADnpIroUSf4qto5baNK'";

            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query))
            {
                cst.executeUpdate();
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return false;
    }
}
