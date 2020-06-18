package org.csu.mypetstore.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CORSConfig extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
        super.addCorsMappings(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        // addResourceLocations是图片存放的真实路径
        registry.addResourceHandler("/images/**").addResourceLocations("/static/images/");
        super.addResourceHandlers(registry);
    }

}
