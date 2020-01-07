package com.PTS3S36G3.FriendChatService.DAL.Context;

import com.PTS3S36G3.FriendChatService.DAL.Interface.IFriendChatContext;
import com.PTS3S36G3.FriendChatService.Models.Chat;
import com.PTS3S36G3.FriendChatService.Models.Message;
import java.sql.*;

public class FriendChatContext implements IFriendChatContext {
    private String url = "jdbc:mysql://remotemysql.com/c9YV0IGlCn";
    private String username = "c9YV0IGlCn";
    private String password = "Uqd2Ch4yIw";

    @Override
    public Chat LoadChat(Chat chat)
    {

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            String query = "SELECT chatid, userid, message, timestamp FROM message WHERE chatid = ?";

            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query))
            {
                cst.setInt(1, chat.getChatId());

                try (ResultSet rs = cst.executeQuery(query))
                {
                    // iterate through the java resultset
                    while (rs.next())
                    {
                        Message message = new Message();
                        message.setMessage(rs.getString("message"));
                        message.setUserid(rs.getInt("userid"));
                        message.setTimeStamp(rs.getString("timestamp"));
                        chat.AddMessageToAllMessagesList(message);
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return chat;
    }

    @Override
    public boolean DeleteChat(Chat chat)
    {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM chat WHERE userid_one = ? AND userid_two = ? ";
            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query)) {
                cst.setInt(1, chat.getUser_one().getId());
                cst.setInt(2, chat.getUser_two().getId());
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
    public boolean CreateChat(Chat chat) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO chat(userone, usertwo) VALUES(?,?)";
            // create the java statement
            try (PreparedStatement cst = conn.prepareStatement(query)) {
                cst.setInt(1, chat.getUser_one().getId());
                cst.setInt(2, chat.getUser_two().getId());
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
