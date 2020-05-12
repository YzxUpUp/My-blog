package cn.yzx.community.controller;

import cn.yzx.community.mapper.blogMapper;
import cn.yzx.community.pojo.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class valueController {
    @Autowired
    private blogMapper mapper;

    @GetMapping("/value")
    public String showValue(@RequestParam(defaultValue = "1") int id,
                            Model model,
                            HttpSession session){
        blog blog = mapper.getBlog(id);
        Object viewMark = session.getAttribute("viewMark");
        if(viewMark == null){
            mapper.addView(blog);
            session.setAttribute("viewMark","1");
        }
        model.addAttribute("blog",blog);
        return "value";
    }
}
