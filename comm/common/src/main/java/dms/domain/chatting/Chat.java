package dms.domain.chatting;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class Chat {

    @Column(name = "chatId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "chatRoomId")
    @OneToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @JoinColumn(name = "writerId")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoomMember writer;

    private String message;

    protected static Chat createChat(ChatRoom chatRoom, ChatRoomMember chatRoomMember, String message){
        Chat chat = new Chat();
        chat.chatRoom = chatRoom;
        chat.writer = chatRoomMember;
        chat.message =  message;

        return chat;
    }
}
