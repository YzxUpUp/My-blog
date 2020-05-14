package cn.yzx.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //通过重写addInterceptors方法设置拦截器的拦截范围
        //addInterceptor表示添加新的拦截器，参数为拦截器类，此处传入我们自定义的拦截器
        //addPathPatterns用于设置拦截范围，参数为拦截范围，此处设置全部资源拦截
        //excludePathPatterns用于设置不拦截的资源，此处将访问登录页面的请求以及静态资源排除
        registry.addInterceptor(new adminHandler()).addPathPatterns("/admin/**");
    }
}
