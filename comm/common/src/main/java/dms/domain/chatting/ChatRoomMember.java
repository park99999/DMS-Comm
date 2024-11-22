package dms.domain.chatting;

import dms.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class ChatRoomMember {

    @Column(name = "chatRoomMemberId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "chatRoomId")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;


    protected ChatRoomMember(Member member, ChatRoom chatRoom){
        this.member = member;
        this.chatRoom = chatRoom;
    }
}
