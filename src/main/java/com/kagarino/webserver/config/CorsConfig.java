package com.kagarino.webserver.config;

import com.kagarino.webserver.interceptors.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zwj
 * @Date: 2024/6/3 22:45
 * @Version: v1.0.0
 * @Description: TODO 处理跨域
 **/
@Configuration
@EnableWebMvc
public class CorsConfig  implements  WebMvcConfigurer {


    @Autowired
    JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePaths=new ArrayList<>();
        excludePaths.add("/doc.html");
        excludePaths.add("/swagger**/**");
        excludePaths.add("/v3/**");
        excludePaths.add("/webjars/**");
        excludePaths.add("/user/welcome/**");
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                //excludePathPatterns方法（指定排除拦截路径，用于登录等部分开放接口）
                .excludePathPatterns(excludePaths);
    }

    /**
     * @Auther: zwj
     * @Date: 2024/6/3 22:52
     * @Description: TODO 自由访问
     * @Params: registry
     * @Return: void
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
//                .maxAge(3600);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }
}
