package cn.yzx.community.controller;

import cn.yzx.community.mapper.blogMapper;
import cn.yzx.community.mapper.commentMapper;
import cn.yzx.community.pojo.blog;
import cn.yzx.community.pojo.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class valueController {
    @Autowired
    private blogMapper mapper;

    @Autowired
    private commentMapper cmapper;

    @GetMapping("/value")
    public String showValue(@RequestParam(defaultValue = "1") int id,
                            Model model,
                            HttpSession session){
        blog blog = mapper.getBlog(id);
        int commentCount = mapper.getCommentCount(id);
        mapper.addCommentCount(commentCount,id);
        blog.setCommentCount(commentCount);
        List<comment> comments = cmapper.getComment((long) id);
        Object viewMark = session.getAttribute("viewMark");
        if(viewMark == null){
            mapper.addView(blog);
            session.setAttribute("viewMark",id);
        }else{
            if((int)viewMark != id){
                mapper.addView(blog);
                session.setAttribute("viewMark",id);
            }
        }
        model.addAttribute("comments",comments);
        model.addAttribute("blog",blog);
        return "value";
    }
}
