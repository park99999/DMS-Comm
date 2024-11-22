package dms.service;

import dms.domain.Member;
import dms.domain.chatting.ChatRoom;
import dms.domain.chatting.ChatRoomMember;
import dms.dto.ChatAddDto;
import dms.repository.ChatRoomRepository;
import dms.repository.MemberRepository;
import dms.socket.SocketServiceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ChattingService {

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final SocketServiceProvider socketServiceProvider;

    @Transactional
    public Long addChatRoom(Long memberId, Long targetId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Member targetMember = memberRepository.findById(targetId).orElseThrow();

        ChatRoom chatRoom = ChatRoom.create(member, targetMember);
        chatRoomRepository.save(chatRoom);

        return chatRoom.getId();
    }

    @Transactional
    public void addChat(Long memberId, Long chatRoomId, ChatAddDto chatAddDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();
        ChatRoomMember chatRoomMember = chatRoomRepository.findChatRoomMember(chatRoomId, memberId).orElseThrow();
        ChatRoomMember chatRoomTarget = chatRoomRepository.findChatRoomMember(chatRoomId, chatAddDto.getTargetId()).orElseThrow();
        chatRoom.addChat(chatAddDto.getMessage(),chatRoomMember);

        socketServiceProvider.sendMessage(chatRoomTarget.getMember().getId(), chatAddDto.getMessage());
    }
}
