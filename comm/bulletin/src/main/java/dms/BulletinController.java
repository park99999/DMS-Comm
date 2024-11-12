package dms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Member;

@RequiredArgsConstructor
@Controller
public class BulletinController {

    private final Member member;
}
