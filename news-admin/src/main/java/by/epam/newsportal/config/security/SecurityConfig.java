package by.epam.newsportal.config.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by Hanna_Blizniova on 9/14/2016.
 */
@Configuration
@ComponentScan("by.epam.newsportal")
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private static final String USERS_BY_USERNAME_QUERY =
            "select login, password, 1 as enabled from users  where login = ?";
    private static final String AUTHORITIES_BY_USERNAME_QUERY  =
            "select users.login, role.role_name  from users  " +
                    "join role  on users.role_id = role.role_id " +
                    "where users.login = ?";

    @Resource(name = "dataSource")
    private DataSource dataSource;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
             //   .passwordEncoder(new Md5PasswordEncoder())
                .usersByUsernameQuery(USERS_BY_USERNAME_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/goToLoginPage").permitAll()
                .antMatchers("/allNews").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login").failureUrl("/login?")
                   // .loginProcessingUrl("/j_spring_security_check")
                    .usernameParameter("login").passwordParameter("password")
                    .defaultSuccessUrl("/allNews", true)
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")

                    .logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
    }
}