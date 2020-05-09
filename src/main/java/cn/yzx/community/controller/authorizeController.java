package cn.yzx.community.controller;

import cn.yzx.community.dto.AccessTokenDTO;
import cn.yzx.community.dto.GithubUser;
import cn.yzx.community.mapper.userMapper;
import cn.yzx.community.pojo.User;
import cn.yzx.community.provider.GithubProvier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class authorizeController {

    @Autowired
    private GithubProvier githubProvier;

    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectURI;

    @Autowired
    private userMapper mapper;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpSession session) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectURI);
        String accessToken = githubProvier.getAccessToken(accessTokenDTO);
        GithubUser gituser = githubProvier.getUser(accessToken);
        if(gituser != null){
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gituser.getName());
            user.setAccounId(String.valueOf(gituser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            mapper.saveUser(user);
            //登录成功，将user对象共享到到页面
            session.setAttribute("user",gituser);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }

}
