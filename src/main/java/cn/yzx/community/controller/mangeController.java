package cn.yzx.community.controller;

import cn.yzx.community.mapper.blogMapper;
import cn.yzx.community.pojo.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class mangeController {
    @Autowired
    private blogMapper mapper;

    @GetMapping("/mange")
    public String mange(Model model) {
        List<blog> allBolg = mapper.getAllBolg();
        model.addAttribute("allBlog", allBolg);
        return "mange";
    }

    @GetMapping("/del")
    public String delete(@RequestParam int id) {
        mapper.deleteBlog(id);
        return "redirect:/admin/mange";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id,
                       Model model) {
        blog blog = mapper.getBlog(id);
        model.addAttribute("blog",blog);
        return "edit";
    }

    @PostMapping("/edit")
    public String doEdit(blog b) {
        mapper.updateBlog(b);
        return "redirect:/admin/mange";
    }
}
