package com.human.web_board.controller;

import com.human.web_board.dto.CommentCreateReq;
import com.human.web_board.dto.MemberRes;
import com.human.web_board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
