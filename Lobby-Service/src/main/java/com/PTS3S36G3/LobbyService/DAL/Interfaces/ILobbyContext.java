package com.PTS3S36G3.LobbyService.DAL.Interfaces;

import com.PTS3S36G3.LobbyService.Models.Lobbies;
import com.PTS3S36G3.LobbyService.Models.Lobby;
import com.PTS3S36G3.LobbyService.Models.User;

public interface ILobbyContext
{
    Lobby AddLobby(Lobby lobby, User user);

    Lobby AddLobbyWithPassword(Lobby lobby, User user);

    boolean DeleteLobby(Lobby lobby);

    Lobby JoinLobby(Lobby lobby, User user);

    Lobby LeaveLobby(Lobby lobby, User user);

    Lobbies GetLobbies();

    Lobby GetLobbyById(int lobbyId);
}
