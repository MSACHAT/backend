package MSACHAT.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/avatar/**").addResourceLocations("file:D:/A/data/PostImages/");
        registry.addResourceHandler("/uploads/postimages/**").addResourceLocations("file:D:/A/data/PostImages/");
        // TODO:"file:"后面改成自己本地想保存的路径
    }
}
