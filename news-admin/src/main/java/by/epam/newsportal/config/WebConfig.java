package by.epam.newsportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by Hanna_Blizniova on 9/1/2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan("by.epam.newsportal")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tiles = new TilesConfigurer();
        tiles.setDefinitions(new String[]{"/WEB-INF/tiles.xml"});
        tiles.setCheckRefresh(true);
        return tiles;
    }
    @Bean
    public ViewResolver viewResolver() {
        return new TilesViewResolver();
    }
}
