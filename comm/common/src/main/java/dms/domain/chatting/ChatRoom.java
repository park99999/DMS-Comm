package dms.domain.chatting;

import dms.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ChatRoom {

    @Column(name = "chatRoomId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatRoomMember> chatRoomMembers = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<Chat> chats = new ArrayList<>();

    public static ChatRoom create(Member member, Member targetMember){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.chatRoomMembers.add(new ChatRoomMember(member, chatRoom));
        chatRoom.chatRoomMembers.add(new ChatRoomMember(targetMember, chatRoom));

        return chatRoom;
    }

    public Chat addChat(String message, ChatRoomMember chatRoomMember){
        if (chatRoomMember.getChatRoom().getId().equals(this.id)) {
            Chat chat = Chat.createChat(this, chatRoomMember, message);
            this.chats.add(chat);
            return chat;
        }

        return null;
    }

    public List<Chat> getChats() {
        return new ArrayList<>(chats);
    }

    public List<ChatRoomMember> getChatRoomMembers() {
        return new ArrayList<>(chatRoomMembers);
    }
}
