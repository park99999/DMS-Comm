package dms.controller;

import dms.dto.ChatAddDto;
import dms.dto.ChatRoomAddDto;
import dms.service.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class ChattingController {

    private final ChattingService chattingService;

    @GetMapping
    public String test(){
        log.info("/members 호출 성공");
        return "ok";
    }

    @PostMapping("/{memberId}/chatRooms")
    public Long addChatRoom(@PathVariable Long memberId,
                            @RequestBody ChatRoomAddDto chatRoomAddDto){

        return chattingService.addChatRoom(memberId, chatRoomAddDto.getTargetId());
    }

    @PostMapping("/{memberId}/chatRooms/{chatRoomId}")
    public String addChat(@PathVariable Long memberId,
                          @PathVariable Long chatRoomId,
                          @RequestBody ChatAddDto chatAddDto){
        chattingService.addChat(memberId, chatRoomId, chatAddDto);

        return "OK";
    }
}
