package cn.yzx.community.controller;

import cn.yzx.community.dto.AccessTokenDTO;
import cn.yzx.community.dto.GithubUser;
import cn.yzx.community.provider.GithubProvier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
        GithubUser user = githubProvier.getUser(accessToken);
        if(user != null){
            //登录成功，将user对象共享到到页面
            session.setAttribute("user",user);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }

}
