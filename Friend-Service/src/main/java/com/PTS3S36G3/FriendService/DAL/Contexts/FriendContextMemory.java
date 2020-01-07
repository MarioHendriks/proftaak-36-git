package com.PTS3S36G3.FriendService.DAL.Contexts;

import com.PTS3S36G3.FriendService.DAL.Interfaces.Context.IFriendContext;
import com.PTS3S36G3.FriendService.Models.Enums.Status;
import com.PTS3S36G3.FriendService.Models.Relationship;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendContextMemory implements IFriendContext
{
    private String url = "jdbc:mysql://remotemysql.com/c9YV0IGlCn";
    private String username = "c9YV0IGlCn";
    private String password = "Uqd2Ch4yIw";


    @Override
    public List<Relationship> GetRelationships()
    {
        List<Relationship> relationships = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT id, friend_one_id, friend_two_id, status FROM relationship_test;";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                int friendOne = rs.getInt("friend_one_id");
                int friendTwo = rs.getInt("friend_two_id");
                Status status = Status.valueOf(rs.getString("status"));
                Relationship relationship = new Relationship(id, friendOne, friendTwo, status);
                relationships.add(relationship);
            }
            st.close();
            conn.close();

        } catch (Exception e) {
            System.err.println(e);
        }
        return relationships;
    }

    @Override
    public boolean AddFriend(int userId, int friendId)
    {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO relationship_test(friend_one_id, friend_two_id, status) VALUES(?, ?, ?)";

            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query)) {
                cst.setInt(1, userId);
                cst.setInt(2, friendId);
                cst.setString(3, Status.Pending.toString());
                cst.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean DeleteFriend(int userId, int friendId)
    {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM relationship_test WHERE friend_one_id = ? AND friend_two_id = ? OR friend_one_id = ? AND friend_two_id = ?";

            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query)) {
                cst.setInt(1, userId);
                cst.setInt(2, friendId);
                cst.setInt(3, friendId);
                cst.setInt(4, userId);
                cst.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean AcceptFriend(int userId, int friendId)
    {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE relationship_test SET status = ? WHERE friend_one_id = ? AND friend_two_id = ? OR friend_one_id = ? AND friend_two_id = ?";

            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query)) {
                cst.setString(1, Status.Accepted.toString());
                cst.setInt(2, userId);
                cst.setInt(3, friendId);
                cst.setInt(4, friendId);
                cst.setInt(5, userId);
                cst.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean DeclineFriend(int userId, int friendId)
    {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE relationship_test SET status = ? WHERE friend_one_id = ? AND friend_two_id = ? OR friend_one_id = ? AND friend_two_id = ?";

            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query)) {
                cst.setString(1, Status.Declined.toString());
                cst.setInt(2, userId);
                cst.setInt(3, friendId);
                cst.setInt(4, friendId);
                cst.setInt(5, userId);
                cst.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
}
