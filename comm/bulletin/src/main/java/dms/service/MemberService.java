package dms.service;

import dms.domain.Member;
import dms.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private ApplicationContext context;

    public Member createMember(Member member) {
        if(Objects.equals(member.getMemberName(), "exit")){
            //서버 종료
            SpringApplication.exit(context, () -> 0);
        }
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

}
