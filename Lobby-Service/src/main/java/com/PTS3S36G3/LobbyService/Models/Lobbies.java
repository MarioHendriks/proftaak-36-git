package com.PTS3S36G3.LobbyService.Models;

import java.util.ArrayList;
import java.util.List;

public class Lobbies
{
    private List<Lobby> lobbies;

    public Lobbies(){
        this.lobbies = new ArrayList<>();
    }

    public List<Lobby> getLobbies()
    {
        return lobbies;
    }

    public void addLobby(Lobby lobby){
        this.lobbies.add(lobby);
    }


}
