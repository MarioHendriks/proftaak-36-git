package com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Setup;

import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketServlet extends org.eclipse.jetty.websocket.servlet.WebSocketServlet
{
    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.register(WebSocketJetty.class);
    }
}
