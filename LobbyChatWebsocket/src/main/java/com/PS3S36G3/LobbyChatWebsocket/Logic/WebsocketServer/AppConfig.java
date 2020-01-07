package com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer;

import com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Setup.ServiceBean;
import com.PS3S36G3.LobbyChatWebsocket.Logic.WebsocketServer.Setup.WebSocketServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig
{
    @Bean
    public ServletRegistrationBean socketServlet(){
        return new ServletRegistrationBean(new WebSocketServlet(), "/ws/");
    }

    @Bean
    public ServiceBean randomNameBeanUtil(){
        return new ServiceBean();
    }
}