package com.gubijins.security.config;


import com.gubijins.security.common.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author ：gubijins
 * @date ：Created in 2020/6/26 22:42
 */

//
//@Configuration
////public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
//public class WebMvcConfiguration extends WebMvcConfigurationSupport {
//
//        @Autowired
//        HttpInterceptor httpInterceptor;
//
//        @Override
//        public void addInterceptors(InterceptorRegistry registry) {
////             addPathPatterns 用于添加拦截规则
////             excludePathPatterns 用户排除拦截
////             映射为 user 的控制器下的所有映射
//            registry.addInterceptor(httpInterceptor).addPathPatterns("/admin/*").excludePathPatterns("/index", "/");
//            registry.addInterceptor(httpInterceptor);
//            super.addInterceptors(registry);
//        }

//        @Override
//        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/**")
//                    .addResourceLocations("classpath:/");
//        }
//}
