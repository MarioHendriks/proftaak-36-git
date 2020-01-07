package com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer;
import com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Model.*;

import java.util.ArrayList;
import java.util.List;

public class LobbyLogic
{
    private static List<Lobby> lobbies = new ArrayList<>();
    private static int lobbyCount;

    public void CreateLobby(User user){
        Lobby lobby = new Lobby(lobbyCount, user);
        lobbyCount++;
        lobbies.add(lobby);
        System.out.println("New lobby created by " + user.getUsername() + " with ID: " + lobby.getId());
    }

    public List<Lobby> getLobbies(){
        return lobbies;
    }

    public Lobby joinLobby(User user, int lobbyIdToJoin){
        List<Lobby> lobbyList = new ArrayList<>();
        lobbyList = lobbies;

        System.out.println("Trying to join lobby with id: " + lobbyIdToJoin);

        for(Lobby lobby : lobbyList){
            if (lobbyIdToJoin == lobby.getId())
            {
                lobby.addUser(user);
                System.out.println("Joined lobby successfully");
                return lobby;
            }
        }

        System.out.println("Failed to join lobby");
        return new Lobby(0, user = new User(0, "No lobby found"));
    }

    public void leaveLobby(User user){
        List<Lobby> lobbyList = new ArrayList<>();
        lobbyList = lobbies;

        //Check in which lobby the user is
        for(Lobby lobby : lobbyList){
            for(User currentUser : lobby.getUsers()){
                if(currentUser.getId() == user.getId()){
                    //Removes this user from lobby
                }
            }
        }
    }
}
