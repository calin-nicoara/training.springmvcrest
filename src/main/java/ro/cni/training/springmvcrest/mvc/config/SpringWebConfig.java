package ro.cni.training.springmvcrest.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("/home/calin-nicoara/Projects/courses/java_courses/" +
                        "springmvcrest/src/main/resources/static/img/");
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry
                .addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }
}
