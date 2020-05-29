package cn.yzx.community.controller;

import cn.yzx.community.mapper.blogMapper;
import cn.yzx.community.mapper.commentMapper;
import cn.yzx.community.pojo.blog;
import cn.yzx.community.pojo.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
                            HttpSession session,
                            HttpServletRequest request) {
        //根据传入id，与comment表联合查询获取总评论数
        int commentCount = mapper.getCommentCount(id);

        //更新指定id的blog的总评论数
        mapper.addCommentCount(commentCount, id);

        //根据传入id获取blog对象
        blog blog = mapper.getBlog(id);

        //获取评论集合
        List<comment> comments = cmapper.getComment((long) id);

        //获取session域中名为viewMark的共享数据
        Object viewMark = session.getAttribute("viewMark");
        //当第一次访问，session域中没有viewMark数据，表示用户是第一次访问，增加一次浏览数，并为viewMark数据赋值
        if (viewMark == null) {
            mapper.addView(blog);
            session.setAttribute("viewMark", id);
        } else { //否则viewMark数据存在，表示用户在同一次会话中，不增加浏览数
            if ((int) viewMark != id) { //当viewMark数据的值与当前id不同，表示用户看了另一篇博客，增加浏览数并重新赋值
                mapper.addView(blog);
                session.setAttribute("viewMark", id);
            }
        }

        //当评论数为0时，共享一条数据
        if (comments.size() == 0) {
            model.addAttribute("nonComm", "没有评论，不如发一条？");
        }

        //装载标签数据
        String[] tags = blog.getTag().split(",");
        blog.setTags(tags);

        String ua = request.getHeader("user-agent"); //通过ua判断是否为手机终端
        if(ua.contains("Android") || ua.contains("iPhone") || ua.contains("iPod") || ua.contains("ios")) {
            model.addAttribute("isPhone", 1);
        }

        model.addAttribute("comments", comments);
        model.addAttribute("blog", blog);
        return "value";
    }
}
