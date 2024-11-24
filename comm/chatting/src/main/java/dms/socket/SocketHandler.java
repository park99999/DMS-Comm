package dms.socket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class SocketHandler extends TextWebSocketHandler {

    private final SocketManager socketManager;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        String query = session.getUri().getQuery(); // "userId=67890"
        Map<String, String> params = Arrays.stream(query.split("&"))
                .map(param -> param.split("="))
                .collect(Collectors.toMap(p -> p[0], p -> p[1]));

        Long userId = Long.parseLong(params.get("userId"));
        log.info("userId = {}", userId);
        socketManager.addSocket(userId, session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long userId = socketManager.deleteSocket(session);
        super.afterConnectionClosed(session, status);
    }
}
