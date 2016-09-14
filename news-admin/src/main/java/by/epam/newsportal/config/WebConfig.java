package by.epam.newsportal.config;

import by.epam.newsportal.config.security.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        driverManagerDataSource.setUsername("NEW_USER");
        driverManagerDataSource.setPassword("newuser");
        return driverManagerDataSource;
    }
}
