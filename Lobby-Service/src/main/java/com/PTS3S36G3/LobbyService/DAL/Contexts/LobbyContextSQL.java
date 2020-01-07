package com.PTS3S36G3.LobbyService.DAL.Contexts;

import com.PTS3S36G3.LobbyService.DAL.Interfaces.ILobbyContext;
import com.PTS3S36G3.LobbyService.Models.Lobbies;
import com.PTS3S36G3.LobbyService.Models.Lobby;
import com.PTS3S36G3.LobbyService.Models.User;
import java.sql.*;

public class LobbyContextSQL implements ILobbyContext
{
    private String url;
    private String username;
    private String password;

    public LobbyContextSQL()
    {
        this.url = "jdbc:mysql://77.251.225.24/microservicesg3";
        this.username = "microservicesg3";
        this.password = "OzOc5NHrMoidwD3VkzzY";
    }

    public Lobby AddLobby(Lobby lobby, User user)
    {
        // create our mysql database connection
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            // SQL query calling stored procedure
            String query = "{call AddLobby(?, ?, ?)}";

            // create the java statement and add the parameters
            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setString(1, lobby.getName());
                cst.setString(2, lobby.getDescription());
                cst.setInt(3, user.getId());

                // get the result and parse them to the object
                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        lobby.setId(rs.getInt("id"));
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return lobby;
    }

    public Lobby AddLobbyWithPassword(Lobby lobby, User user)
    {
        // create our mysql database connection
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            // SQL query calling stored procedure
            String query = "{call AddLobbyWithPassword(?, ?, ?, ?)}";

            // create the java statement and add the parameters
            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setString(1, lobby.getName());
                cst.setString(2, lobby.getDescription());
                cst.setString(3, lobby.getPassword());
                cst.setInt(4, user.getId());

                // get the result and parse them to the object
                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        lobby.setId(rs.getInt("id"));
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return lobby;
    }

    public boolean DeleteLobby(Lobby lobby)
    {
        // create our mysql database connection
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            // SQL query calling stored procedure
            String query = "delete from lobby where id = ?;";

            // create the java statement and add the parameters
            try (PreparedStatement cst = conn.prepareStatement(query))
            {
                cst.setInt(1, lobby.getId());
                cst.executeUpdate();
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }

    public Lobby JoinLobby(Lobby lobby, User user)
    {
        String userNumber = GetUserNumber(lobby);

        // create our mysql database connection
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            // SQL query calling stored procedure
            String query = "update lobby set " + userNumber + " = ?;";

            // create the java statement and add the parameters
            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getId());
                pst.executeUpdate();
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        switch (userNumber)
        {
            case "user_one_id":
                lobby.setUserOne(user);
                break;
            case "user_two_id":
                lobby.setUserTwo(user);
                break;
            case "user_three_id":
                lobby.setUserThree(user);
                break;
            case "user_four_id":
                lobby.setUserFour(user);
                break;
        }

        return lobby;
    }

    private String GetUserNumber(Lobby lobby)
    {
        if (lobby.getUserOne().getId() == 0)
        {
            return "user_one_id";
        } else if (lobby.getUserTwo().getId() == 0)
        {
            return "user_two_id";
        } else if (lobby.getUserThree().getId() == 0)
        {
            return "user_three_id";
        } else if (lobby.getUserFour().getId() == 0)
        {
            return "user_four_id";
        }

        return "Lobby Full";
    }

    private String GetUserNumber(Lobby lobby, User user)
    {
        if (lobby.getUserOne().getId() == user.getId())
        {
            return "user_one_id";
        } else if (lobby.getUserTwo().getId() == user.getId())
        {
            return "user_two_id";
        } else if (lobby.getUserThree().getId() == user.getId())
        {
            return "user_three_id";
        } else if (lobby.getUserFour().getId() == user.getId())
        {
            return "user_four_id";
        }

        return "Not In Lobby";
    }

    public Lobby LeaveLobby(Lobby lobby, User user)
    {
        String userNumber = GetUserNumber(lobby, user);

        if (userNumber.equals("Not In Lobby"))
        {
            return new Lobby(0, "Not In Lobby");
        }

        // create our mysql database connection
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            // SQL query calling stored procedure
            String query = "update lobby set " + userNumber + " = 0 " +
                    "where id = ?;";

            // create the java statement and add the parameters
            try (PreparedStatement cst = conn.prepareStatement(query))
            {
                cst.setInt(1, lobby.getId());
                cst.executeUpdate();
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        switch (userNumber)
        {
            case "user_one_id":
                lobby.setUserOne(null);
                break;
            case "user_two_id":
                lobby.setUserTwo(null);
                break;
            case "user_three_id":
                lobby.setUserThree(null);
                break;
            case "user_four_id":
                lobby.setUserFour(null);
                break;
        }

        return lobby;
    }

    public Lobbies GetLobbies()
    {
        Lobbies lobbies = new Lobbies();

        // create our mysql database connection
        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            // SQL query calling stored procedure
            String query = "select `id`, `name`, `description`, `has_password`, `password`, `user_one_id`, `user_two_id`, `user_three_id`, `user_four_id` " +
                    "from `lobby`;";

            // create the java statement
            try (Statement st = conn.createStatement())
            {
                try (ResultSet rs = st.executeQuery(query))
                {
                    while (rs.next())
                    {
                        Lobby lobby = new Lobby();
                        lobby.setId(rs.getInt("id"));
                        lobby.setName(rs.getString("name"));
                        lobby.setDescription(rs.getString("description"));
                        lobby.setHasPassword(rs.getBoolean("has_password"));
                        lobby.setPassword(rs.getString("password"));
                        lobby.setUserOne(rs.getInt("user_one_id"));
                        lobby.setUserTwo(rs.getInt("user_two_id"));
                        lobby.setUserThree(rs.getInt("user_three_id"));
                        lobby.setUserFour(rs.getInt("user_four_id"));
                        lobbies.addLobby(lobby);
                    }
                }
            }
        } catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return lobbies;
    }

    public Lobby GetLobbyById(int lobbyId)
    {
        Lobby lobby = new Lobby();

        try (Connection conn = DriverManager.getConnection(url, username, password))
        {
            String query = "select `id`, `name`, `description`, `has_password`, `password`, `user_one_id`, `user_two_id`, `user_three_id`, `user_four_id` " +
                    "from lobby " +
                    "where id = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, lobbyId);

                try (ResultSet rs = pst.executeQuery())
                {
                    // iterate through the java resultset
                    while (rs.next())
                    {
                        lobby.setId(rs.getInt("id"));
                        lobby.setName(rs.getString("name"));
                        lobby.setDescription(rs.getString("description"));
                        lobby.setHasPassword(rs.getObject("has_password", boolean.class));
                        lobby.setPassword(rs.getString("password"));
                        lobby.setUserOne(rs.getInt("user_one_id"));
                        lobby.setUserTwo(rs.getInt("user_two_id"));
                        lobby.setUserThree(rs.getInt("user_three_id"));
                        lobby.setUserFour(rs.getInt("user_four_id"));
                    }
                }
            }

            return lobby;
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return null;
    }
}
