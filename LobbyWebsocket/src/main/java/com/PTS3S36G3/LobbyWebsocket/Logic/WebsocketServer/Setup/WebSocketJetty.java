package com.PTS3S36G3.LobbyWebsocket.Logic.WebsocketServer.Setup;

import com.PTS3S36G3.LobbyWebsocket.Logic.WebsocketServer.Collection.UserCollection;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

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
    public void onMessage(Session session, String text){
        ServiceBean.getService().sendMessage(session, text);
    }

}
