package org.project.AnnotationHandler;

import org.project.UI.Menu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.ArrayList;


@ComponentScan(basePackages = {"org.project.Dao","org.project.Service","org.project.Controller","org.project.UI"})
@Configuration
@PropertySource("classpath:/config.properties")
public class Config {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static Menu menu(){
        return new Menu("Root Menu", new ArrayList<>());
    }

}
