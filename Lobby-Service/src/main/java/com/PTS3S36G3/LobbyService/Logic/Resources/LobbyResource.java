package com.PTS3S36G3.LobbyService.Logic.Resources;

import com.PTS3S36G3.LobbyService.Logic.LobbyLogic;
import com.PTS3S36G3.LobbyService.Models.Lobbies;
import com.PTS3S36G3.LobbyService.Models.Lobby;
import com.PTS3S36G3.LobbyService.Models.User;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/lobby")
public class LobbyResource
{
    private LobbyLogic lobbyLogic = new LobbyLogic();

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Lobby AddLobby(@RequestBody String jsonString)
    {
        JSONObject jsonObject = new JSONObject(jsonString);

        int userId = (int)jsonObject.get("userId");
        User user = new User(userId);

        Lobby lobby = new Lobby();
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        String password = jsonObject.getString("password");

        lobby.setName(name);
        lobby.setDescription(description);
        lobby.setPassword(password);

        return lobbyLogic.AddLobby(lobby, user);
    }

    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public boolean DeleteLobby(@RequestBody String jsonString)
    {
        JSONObject jsonObject = new JSONObject(jsonString);

        int lobbyId = (int)jsonObject.get("lobbyId");

        Lobby lobby = new Lobby(lobbyId);

        return lobbyLogic.DeleteLobby(lobby);
    }

    @PostMapping(value = "/join", consumes = "application/json", produces = "application/json")
    public Lobby JoinLobby(@RequestBody String jsonString)
    {
        JSONObject jsonObject = new JSONObject(jsonString);

        int lobbyId = (int)jsonObject.get("lobbyId");
        int userId = (int)jsonObject.get("userId");

        Lobby lobby = new Lobby(lobbyId);
        User user = new User(userId);

        String password = "";

        try
        {
            password = jsonObject.getString("password");
        } catch (Exception e)
        {}

        if (password != "")
        {
            return lobbyLogic.JoinLobbyWithPassword(lobby, user, password);
        }

        return lobbyLogic.JoinLobby(lobby, user);
    }

    @PostMapping(value = "/leave", consumes = "application/json", produces = "application/json")
    public Lobby LeaveLobby(@RequestBody String jsonString)
    {
        JSONObject jsonObject = new JSONObject(jsonString);

        int lobbyId = (int)jsonObject.get("lobbyId");
        int userId = (int)jsonObject.get("userId");

        Lobby lobby = new Lobby(lobbyId);
        User user = new User(userId);
        lobby.setUserOne(user);

        return lobbyLogic.LeaveLobby(lobby, user);
    }

    @GetMapping(value = "/get")
    public Lobbies getLobbies()
    {
        return lobbyLogic.GetLobbies();
    }

    @GetMapping(value = "/getById/{lobbyId}")
    public Lobby getLobbyById(@PathVariable("lobbyId")int lobbyId)
    {
        return lobbyLogic.GetLobbyById(lobbyId);
    }
}