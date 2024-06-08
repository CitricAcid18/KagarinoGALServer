package com.kagarino.webserver.Config;

import com.kagarino.webserver.interceptors.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: zwj
 * @Date: 2024/6/3 22:45
 * @Version: v1.0.0
 * @Description: TODO 处理跨域
 **/
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {


    @Autowired
    JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**");
    }

    /**
     * @Auther: zwj
     * @Date: 2024/6/3 22:52
     * @Description: TODO 自由访问
     * @Params: registry
     * @Return: void
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .maxAge(3600);
    }
}
