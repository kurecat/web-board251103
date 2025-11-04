package com.human.web_board.controller;

import com.human.web_board.dto.CommentCreateReq;
import com.human.web_board.dto.CommentRes;
import com.human.web_board.dto.MemberRes;
import com.human.web_board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;

@Controller @RequiredArgsConstructor @RequestMapping("comments")
public class CommentController {
    private final CommentService commentService;

//     댓글 등록
    @PostMapping("{postId}")
    public String create(@PathVariable Long postId, CommentCreateReq req, HttpSession session){
        MemberRes member = (MemberRes) session.getAttribute("loginMember");
        if (session.getAttribute("loginMember") == null) return "redirect:/";
        req.setPostId(postId);
        req.setMemberId(member.getId());
        commentService.write(req);
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/{commentId}/delete") // ‼️ URL 변경
    public String delete(
            @PathVariable("commentId") Long commentId,     // ‼️ 삭제할 댓글 ID
            @RequestParam("postId") Long postId,           // ‼️ 돌아갈 게시글 ID
            HttpSession session
    ){
        MemberRes member = (MemberRes) session.getAttribute("loginMember");
        if (member == null) { // "loginMember" 체크 (일관성)
            return "redirect:/";
        }

        // ‼️ 서비스에 정확한 "commentId"를 전달하여 하나만 삭제
        commentService.delete(commentId);

        // ‼️ "postId"로 상세 페이지에 리다이렉트
        return "redirect:/posts/" + postId;
    }
}
