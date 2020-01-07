package com.PTS3S36G3.LobbyWebsocket.Logic.WebsocketServer.MessageHandler;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler
{
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void handleMessage(Session session, JSONObject message)
    {
        System.out.println("magisch");
        switch (message.getString("Subject"))
        {
            case "JOINLOBBY":

                //executorService.submit(new UserExecutor(message, session));
                break;
            case "LEAVELOBBY":
                //Doe wat leuks
                break;
            case "SENDLOBBYMESSAGE":
                //Doe wat leuks
                break;
        }
    }
}
