package dms.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class SocketServiceProvider {

    private final SocketManager socketManager;

    public boolean sendMessage(Long userId, String message){
        WebSocketSession socketSession = socketManager.getSocketSession(userId);
        if (socketSession == null) {
            return false;
        }

        try {
            TextMessage textMessage = new TextMessage(message);
            socketSession.sendMessage(textMessage);

            return true;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
