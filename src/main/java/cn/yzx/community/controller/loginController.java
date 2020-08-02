package cn.yzx.community.controller;

import cn.yzx.community.mapper.userMapper;
import cn.yzx.community.pojo.user;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class loginController {
    @Autowired
    private userMapper mapper;

    @GetMapping
    public String login(){
        return "login";
    }

    @PostMapping
    public String checkLoginUser(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpSession session){
//        user user = mapper.getUser(username,password);
        QueryWrapper<user> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username)
                .eq("password",password);
        user user = mapper.selectOne(wrapper);
        if(user != null){
            session.setAttribute("user",user);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/out")
    public String out(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
