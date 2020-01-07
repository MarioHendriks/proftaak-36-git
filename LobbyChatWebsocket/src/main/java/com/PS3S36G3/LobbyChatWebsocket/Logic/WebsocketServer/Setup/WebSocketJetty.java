package com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Setup;

import com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Collection.UserCollection;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class WebSocketJetty
{
    @OnWebSocketConnect
    public void onConnect(Session session) {
        ServiceBean.getService().addSession(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int _closeCode, String _closeReason) {
        ServiceBean.getService().removeSession(session);
        UserCollection.getConnectedUsers().remove(UserCollection.getUserBySession(session));
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String text) throws IOException
    {
        ServiceBean.getService().sendMessage(session, text);
    }

}
