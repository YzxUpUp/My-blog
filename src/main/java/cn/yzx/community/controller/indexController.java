package cn.yzx.community.controller;

import cn.yzx.community.mapper.blogMapper;
import cn.yzx.community.pojo.blog;
import cn.yzx.community.pojo.page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class indexController {

    @Autowired
    private blogMapper mapper;

    @PostMapping("/")
    public String search(@RequestParam(defaultValue = "1") int currentPage,
                         @RequestParam(defaultValue = "100") int rows,
                         Model model,
                         String bloginfo,
                         HttpServletRequest request){
        page p = new page();
        p.setCurrentPage(currentPage); //装载当前页数

        List<blog> allBolg = mapper.getAllBolg();
        int size = allBolg.size(); //获取所有博客数量
        p.setTotalCount(size); //装载博客总数量

        List<String> allTag = new ArrayList<>(); //用于装载全部标签
        for (blog blog : allBolg) {
            String[] tags = blog.getTag().split(",");
            allTag.addAll(0, Arrays.asList(tags));
        }
        List<String> relAllTag = allTag.stream().distinct().collect(Collectors.toList());
        p.setAllTag(relAllTag);

        int start = (currentPage - 1) * rows; //获取起始位置

        int totalPage = size % rows == 0 ? size / rows : size / rows + 1; //判断总页数
        p.setTotalPage(totalPage); //装载总页数

        if(currentPage <= 1){ //判断是否为第一页
            p.setFirstPage(true);
            start = 0;
        }else{
            p.setFirstPage(false);
        }

        if(currentPage >= totalPage){ //判断是否为最后一页
            p.setLastpage(true);
            start = (totalPage - 1) * rows;
        }else{
            p.setLastpage(false);
        }

        List<blog> blogByLike = mapper.getBlogByLike(bloginfo,start,rows);//获取本页显示数据
        List<blog> blogByMostView = mapper.getBlogByMostView();
        if(blogByLike.size() == 0){
            model.addAttribute("non","好像……没有符合条件的博客");
        }

        for (blog blog : blogByLike) { //装载标签数据
            String[] tags = blog.getTag().split(",");
//            System.out.println(Arrays.toString(tags));
            blog.setTags(tags);
        }

        p.setCounts(blogByLike); //装载本页数据

        String ua = request.getHeader("user-agent"); //通过ua判断是否为手机终端
        if(ua.contains("Android") || ua.contains("iPhone") || ua.contains("iPod") || ua.contains("ios")) {
            model.addAttribute("isPhone", 1);
        }

        model.addAttribute("page",p);
        model.addAttribute("mostView",blogByMostView);

        return "index";
    }

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "1") int currentPage,
                        @RequestParam(defaultValue = "10") int rows,
                        HttpServletRequest request){
        page p = new page();
        p.setCurrentPage(currentPage); //装载当前页数

        List<blog> allBolg = mapper.getAllBolg();
        int size = allBolg.size(); //获取所有博客数量
        p.setTotalCount(size); //装载博客总数量

        List<String> allTag = new ArrayList<>(); //用于装载全部标签
        for (blog blog : allBolg) {
            String[] tags = blog.getTag().split(",");
            allTag.addAll(0, Arrays.asList(tags));
        }
        List<String> relAllTag = allTag.stream().distinct().collect(Collectors.toList());
        p.setAllTag(relAllTag);

        int start = (currentPage - 1) * rows; //获取起始位置

        int totalPage = size % rows == 0 ? size / rows : size / rows + 1; //判断总页数
        p.setTotalPage(totalPage); //装载总页数

        if(currentPage <= 1){ //判断是否为第一页
            p.setFirstPage(true);
            start = 0;
        }else{
            p.setFirstPage(false);
        }

        if(currentPage >= totalPage){ //判断是否为最后一页
            p.setLastpage(true);
            start = (totalPage - 1) * rows;
        }else{
            p.setLastpage(false);
        }

        List<blog> byPage = mapper.getByPage(start, rows); //获取本页显示数据
        List<blog> blogByMostView = mapper.getBlogByMostView();
        p.setCounts(byPage); //装载本页数据

        for (blog blog : byPage) { //装载标签数据
            String[] tags = blog.getTag().split(",");
//            System.out.println(Arrays.toString(tags));
            blog.setTags(tags);
        }

        String ua = request.getHeader("user-agent"); //通过ua判断是否为手机终端
        if(ua.contains("Android") || ua.contains("iPhone") || ua.contains("iPod") || ua.contains("ios")) {
            model.addAttribute("isPhone", 1);
        }

        model.addAttribute("page",p);
        model.addAttribute("mostView",blogByMostView);

        return "index";
    }

    @GetMapping("/searchByTag")
    public String index(Model model,
                        @RequestParam(defaultValue = "1") int currentPage,
                        @RequestParam(defaultValue = "100") int rows,
                        @RequestParam String tag,
                        HttpServletRequest request){
        page p = new page();
        p.setCurrentPage(currentPage); //装载当前页数

        List<blog> allBolg = mapper.getAllBolg();
        int size = allBolg.size(); //获取所有博客数量
        p.setTotalCount(size); //装载博客总数量

        List<String> allTag = new ArrayList<>(); //用于装载全部标签
        for (blog blog : allBolg) {
            String[] tags = blog.getTag().split(",");
            allTag.addAll(0, Arrays.asList(tags));
        }
        List<String> relAllTag = allTag.stream().distinct().collect(Collectors.toList());
        p.setAllTag(relAllTag);

        int start = (currentPage - 1) * rows; //获取起始位置

        int totalPage = size % rows == 0 ? size / rows : size / rows + 1; //判断总页数
        p.setTotalPage(totalPage); //装载总页数

        if(currentPage <= 1){ //判断是否为第一页
            p.setFirstPage(true);
            start = 0;
        }else{
            p.setFirstPage(false);
        }

        if(currentPage >= totalPage){ //判断是否为最后一页
            p.setLastpage(true);
            start = (totalPage - 1) * rows;
        }else{
            p.setLastpage(false);
        }

        List<blog> bytag = mapper.getBlogByTag(tag,start, rows); //获取本页显示数据
        List<blog> blogByMostView = mapper.getBlogByMostView();

        p.setCounts(bytag); //装载本页数据

        for (blog blog : bytag) { //装载标签数据
            String[] tags = blog.getTag().split(",");
//            System.out.println(Arrays.toString(tags));
            blog.setTags(tags);
        }

        String ua = request.getHeader("user-agent"); //通过ua判断是否为手机终端
        if(ua.contains("Android") || ua.contains("iPhone") || ua.contains("iPod") || ua.contains("ios")) {
            model.addAttribute("isPhone", 1);
        }

        model.addAttribute("page",p);
        model.addAttribute("mostView",blogByMostView);

        return "index";
    }
}
