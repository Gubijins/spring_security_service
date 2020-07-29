package com.gubijins.security;

import com.gubijins.security.config.AclControlFilter;
import com.gubijins.security.config.LoginFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@MapperScan(basePackages = "com.gubijins.security.mapper")
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    //login_filter过滤器的配置
    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter(){
        FilterRegistrationBean<LoginFilter> registrationBean=new FilterRegistrationBean<>(new LoginFilter());
        //拦截需要判断是否登录的界面
        registrationBean.addUrlPatterns("/sys/*");
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }

    //acl_controller_filter过滤器的配置
    @Bean
    public FilterRegistrationBean<AclControlFilter> aclControlFilter(){
        FilterRegistrationBean<AclControlFilter> registrationBean=new FilterRegistrationBean<>(new AclControlFilter());
        //拦截需要判断是否登录的界面
//        registrationBean.addUrlPatterns("/sys/*");
//        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }




}
