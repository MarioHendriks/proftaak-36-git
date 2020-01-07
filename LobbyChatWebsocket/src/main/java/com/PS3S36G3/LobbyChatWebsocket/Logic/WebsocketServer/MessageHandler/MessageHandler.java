package com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.MessageHandler;

import com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.LobbyLogic;
import com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Model.Lobby;
import com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler
{
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void handleMessage(Session session, JSONObject message) throws IOException
    {
        LobbyLogic lobbyLogic = new LobbyLogic();

        //Gets values from json message
        int lobbyToJoin = message.getInt("LobbyToJoin");
        JSONObject jsonUser = message.getJSONObject("User");

        //Binds jsonobject to a user
        Gson userGson = new Gson();
        User user = userGson.fromJson(jsonUser.toString(), User.class);


        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson1 = gs.create();

        switch (message.getString("Action"))
        {
            case "SENDMESSAGE":
                break;
            case "CREATELOBBY":
                //Creates the lobby with the user
                lobbyLogic.CreateLobby(user);
                break;
            case "LEAVELOBBY":
                break;
            case "JOINLOBBY":
                //Joins lobby and returns the model of the lobby the user joined
                Lobby lobbyAfterUserJoined = lobbyLogic.joinLobby(user, lobbyToJoin);
                JSONObject jsonObjectLobbyAfterJoin = new JSONObject(lobbyAfterUserJoined);
                session.getRemote().sendString(gson1.toJson(jsonObjectLobbyAfterJoin));
                break;
            case "GETLOBBIES":
                //Returns a list of all existing lobbies
                List<Lobby> lobbyList = lobbyLogic.getLobbies();
                JSONObject jsonObject = new JSONObject(lobbyList);
                session.getRemote().sendString(gson1.toJson(lobbyList));
                break;
        }
    }
}
