package cn.yzx.community.controller;

import cn.yzx.community.mapper.commentMapper;
import cn.yzx.community.pojo.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class commentController {
    @Autowired
    private commentMapper mapper;

    @PostMapping("/comment")
    public String comment(comment comm) {
        comm.setType(UUID.randomUUID().toString());
        mapper.addComment(comm);
        return "redirect:/value?id="+comm.getParentId();
    }

    @GetMapping("/delComm")
    public String delComm(@RequestParam String type,
                          @RequestParam int id){
        mapper.delComm(type);
        return "redirect:/value?id="+id;
    }
}
