package cc.lzy.sb.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 聊天室 服务端
 *
 * @author chengyu
 * @version 19/2/21
 */
@ServerEndpoint("/chat-room/{id}")
@Component
public class ChatServerEndPoint {
    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        System.out.println(session.getId());
        sendText(session, id);
    }

    @OnMessage
    public void opMessage(Session session, String message) {
        sendText(session, message);
    }

    private void sendText(Session session, String message) {
        RemoteEndpoint.Basic basic = session.getBasicRemote();
        try {
            basic.sendText("Welcome " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
