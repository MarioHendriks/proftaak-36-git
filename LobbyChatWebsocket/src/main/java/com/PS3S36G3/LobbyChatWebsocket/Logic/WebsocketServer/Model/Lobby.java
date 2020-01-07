package com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Model;

import java.util.ArrayList;
import java.util.List;

public class Lobby
{
    private int id;
    private List<User> users;

    public Lobby(int id, User user)
    {
        this.id = id;
        users = new ArrayList<>();
        users.add(user);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
    }
}
