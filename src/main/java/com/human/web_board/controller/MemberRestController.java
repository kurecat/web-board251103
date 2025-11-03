package com.human.web_board.controller;

import com.human.web_board.dto.MemberRes;
import com.human.web_board.dto.MemberSignupReq;
import com.human.web_board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 외부의 요청을 아파치 톰켓이 수신하고 해당 경로에 해당 하는 메서드 호출, 내부적 JSON 역직렬화를 해줌
@RequiredArgsConstructor // 생성자를 통해서 의존성 주입 받기 위해 사용
@RequestMapping("/api/members")  // http://localhost:8111/api/members
@Slf4j
public class MemberRestController {

    private final MemberService memberService;
    @PostMapping("/signup")   // Post 방식 : 정보를 Body 숨겨서 전송 하는 방식, 정보를 추가할 때 주로 사용
    public ResponseEntity<Long> signup(@RequestBody MemberSignupReq req) {
        log.error("회원 가입 : {}", req);
        return ResponseEntity.ok(memberService.signup(req));
    }

    @GetMapping
    public ResponseEntity<List<MemberRes>> list() {
        return ResponseEntity.ok(memberService.list());
    }

    // 이메일로 회원 조회
    @GetMapping("/{email}")
    public ResponseEntity<MemberRes> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(memberService.getByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberRes> findById(@PathVariable String id) {
        return ResponseEntity.ok(memberService.getByEmail(id));
    }
}
