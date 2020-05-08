package cn.yzx.community.controller;

import cn.yzx.community.dto.AccessTokenDTO;
import cn.yzx.community.dto.GithubUser;
import cn.yzx.community.provider.GithubProvier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authorizeController {

    @Autowired
    private GithubProvier githubProvier;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("3d1541b0273ee796020c");
        accessTokenDTO.setClient_secret("abbb6ff8a83dfff606d1dd6865c98fba89964249");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = githubProvier.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvier.getUser(accessToken);
        System.out.println(user.toString());
        return "index";
    }

}
