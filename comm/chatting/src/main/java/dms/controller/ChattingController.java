package dms.controller;

import dms.dto.ChatAddDto;
import dms.dto.ChatRoomAddDto;
import dms.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/members/{memberId}/chatRooms")
@RequiredArgsConstructor
@RestController
public class ChattingController {

    private final ChattingService chattingService;

    @PostMapping
    public Long addChatRoom(@PathVariable Long memberId,
                            @RequestBody ChatRoomAddDto chatRoomAddDto){

        return chattingService.addChatRoom(memberId, chatRoomAddDto.getTargetId());
    }

    @PostMapping("/{chatRoomId}")
    public String addChat(@PathVariable Long memberId,
                          @PathVariable Long chatRoomId,
                          @RequestBody ChatAddDto chatAddDto){
        chattingService.addChat(memberId, chatRoomId, chatAddDto);

        return "OK";
    }
}
