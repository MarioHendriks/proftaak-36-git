package com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Setup;

import com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.MessageHandler.MessageHandler;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service("WebSocketService")
public class WebSocketService
{
    private MessageHandler messageHandler = new MessageHandler();
    private Set<Session> listenerSessions = new CopyOnWriteArraySet<>();

    public void removeSession(Session session){
        listenerSessions.remove(session);
    }

    public void addSession(Session session){
        listenerSessions.add(session);
    }

    public void sendMessage(Session session, String message) throws IOException
    {
        JSONObject jsonObject = new JSONObject(message);
        messageHandler.handleMessage(session,  jsonObject);
    }

    @PostConstruct
    private void init(){
        Runnable runnable = () -> {
            while (true){

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void sendRandomName(Session s, String message) {
        try {
            s.getRemote().sendString(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
