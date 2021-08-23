package com.social.network.Configs;

import com.social.network.jwt.JWTAuthenticationEntryPoint;
import com.social.network.jwt.JwtFilter;
import com.social.network.jwt.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .exceptionHandling().accessDeniedHandler((request, response, ex) -> {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            printWriter.write("Sorry, you do not have permission to view this page");
            printWriter.close();
        });

                http.authorizeRequests()
                .antMatchers(SecurityConstants.SIGN_UP_URLS).permitAll()
                        .antMatchers("/group").hasRole("ADMIN")
                        .antMatchers("/publication/**").hasRole("ADMIN")
                        .antMatchers("/userRole/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }

    @Bean
    public JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint(){
        return new JWTAuthenticationEntryPoint();
    }
}
