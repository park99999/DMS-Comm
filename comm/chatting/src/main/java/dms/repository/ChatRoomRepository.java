package dms.repository;

import dms.domain.chatting.ChatRoom;
import dms.domain.chatting.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT crm FROM ChatRoomMember crm " +
            "WHERE crm.chatRoom.id = :chatRoomId AND crm.member.id = :userId")
    Optional<ChatRoomMember> findChatRoomMember(Long chatRoomId, Long userId);
}
