package com.PTS3S36G3.LobbyService.DAL.Repositories;

import com.PTS3S36G3.LobbyService.DAL.Contexts.LobbyContextSQL;
import com.PTS3S36G3.LobbyService.DAL.Interfaces.ILobbyContext;
import com.PTS3S36G3.LobbyService.Models.Lobbies;
import com.PTS3S36G3.LobbyService.Models.Lobby;
import com.PTS3S36G3.LobbyService.Models.User;

public class LobbyRepository implements ILobbyContext
{
    private ILobbyContext lobbyContext;

    public LobbyRepository(){
        lobbyContext = new LobbyContextSQL();
    }

    public LobbyRepository(ILobbyContext context){
        this.lobbyContext = context;
    }



    public Lobby AddLobby(Lobby lobby, User user)
    {
        return lobbyContext.AddLobby(lobby, user);
    }

    public Lobby AddLobbyWithPassword(Lobby lobby, User user)
    {
        return lobbyContext.AddLobbyWithPassword(lobby, user);
    }

    public boolean DeleteLobby(Lobby lobby)
    {
        return lobbyContext.DeleteLobby(lobby);
    }

    public Lobby JoinLobby(Lobby lobby, User user)
    {
        return lobbyContext.JoinLobby(lobby, user);
    }

    public Lobby LeaveLobby(Lobby lobby, User user)
    {
        return lobbyContext.LeaveLobby(lobby, user);
    }

    public Lobbies GetLobbies()
    {
        return lobbyContext.GetLobbies();
    }

    public Lobby GetLobbyById(int lobbyId)
    {
        return lobbyContext.GetLobbyById(lobbyId);
    }
}
