package com.PTS3S36G3.LobbyWebsocket.Logic.WebsocketServer.Model;

import org.eclipse.jetty.websocket.api.Session;

public class User
{
    private int id;
    private String username;
    protected Session session;

    public User()
    {
    }

    public User(int id, String username)
    {
        this.id = id;
        this.username = username;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Session getSession()
    {
        return session;
    }

    public void setSession(Session session)
    {
        this.session = session;
    }
}
