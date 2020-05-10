package cn.yzx.community.controller;

import cn.yzx.community.mapper.blogMapper;
import cn.yzx.community.pojo.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class indexController {

    @Autowired
    private blogMapper mapper;

    @GetMapping("/")
    public String index(Model model){
        List<blog> allBolg = mapper.getAllBolg();
        model.addAttribute("blogs",allBolg);
        return "index";
    }
}
