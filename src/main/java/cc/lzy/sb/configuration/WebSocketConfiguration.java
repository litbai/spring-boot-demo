package cc.lzy.sb.configuration;

import cc.lzy.sb.websocket.ChatServerEndPoint;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author chengyu
 * @version 19/2/21
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

//    @Bean
//    public ChatServerEndPoint chatServerEndPoint() {
//        return new ChatServerEndPoint();
//    }
}
