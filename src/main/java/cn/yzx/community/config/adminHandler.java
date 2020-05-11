package cn.yzx.community.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class adminHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session域中获取我们共享的用户信息
        Object user = request.getSession().getAttribute("user");
        //当用户信息不存在时，表示还没有进行登录操作
        if(user == null){
            //返回false表示拦截信息，不放行
            request.setAttribute("error","请先进行登录操作!!");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }else{
            //当session域中存在用户信息时，表示已经登录，直接放行
            return true;
        }
    }
}
