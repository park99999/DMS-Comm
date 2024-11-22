package dms.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SocketManager {

    private final ConcurrentHashMap<Long, WebSocketSession> userSocketMap = new ConcurrentHashMap<>();

    public void addSocket(Long userId, WebSocketSession session){
        userSocketMap.put(userId, session);
    }

    public Long deleteSocket(WebSocketSession session){
        for (Long userId : userSocketMap.keySet()) {
            if(userSocketMap.get(userId).getId().equals(session.getId())){
                userSocketMap.remove(userId);
                return userId;
            }
        }

        return null;
    }

    protected WebSocketSession getSocketSession(Long userId){
        return userSocketMap.get(userId);
    }
}
