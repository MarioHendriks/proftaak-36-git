package com.PTS3S36G3.LobbyService.Logic;

import com.PTS3S36G3.LobbyService.DAL.Repositories.LobbyRepository;
import com.PTS3S36G3.LobbyService.Models.Lobbies;
import com.PTS3S36G3.LobbyService.Models.Lobby;
import com.PTS3S36G3.LobbyService.Models.User;

public class LobbyLogic
{
    private LobbyRepository lobbyRepository = new LobbyRepository();

    public Lobby AddLobby(Lobby lobby, User user)
    {
        if (!lobby.getPassword().equals(""))
        {
            lobby.setHasPassword(true);
            return lobbyRepository.AddLobbyWithPassword(lobby, user);
        }

        return lobbyRepository.AddLobby(lobby, user);
    }

    public boolean DeleteLobby(Lobby lobby)
    {
        return lobbyRepository.DeleteLobby(lobby);
    }

    public Lobby JoinLobby(Lobby lobby, User user)
    {
        lobby = GetLobbyById(lobby.getId());

        if (CheckLobbyFull(lobby))
        {
            return new Lobby(0, "Lobby Full");
        }

        return lobbyRepository.JoinLobby(lobby, user);
    }

    public Lobby JoinLobbyWithPassword(Lobby lobby, User user, String password)
    {
        lobby = GetLobbyById(lobby.getId());

        if (CheckLobbyFull(lobby))
        {
            return new Lobby(0, "Lobby Full");
        }

        if (!lobby.getPassword().equals(password))
        {
            return new Lobby(0, "Wrong Password");
        }

        return lobbyRepository.JoinLobby(lobby, user);
    }

    private boolean CheckLobbyFull(Lobby lobby)
    {
        if (lobby.getUserOne().getId() == 0 || lobby.getUserTwo().getId() == 0
                || lobby.getUserThree().getId() == 0 || lobby.getUserFour().getId() == 0)
        {
            return false;
        }

        return true;
    }



    public Lobby LeaveLobby(Lobby lobby, User user)
    {
        lobby = GetLobbyById(lobby.getId());
        return lobbyRepository.LeaveLobby(lobby, user);
    }

    public Lobbies GetLobbies()
    {
        return lobbyRepository.GetLobbies();
    }

    public Lobby GetLobbyById(int lobbyId)
    {
        return lobbyRepository.GetLobbyById(lobbyId);
    }
}
