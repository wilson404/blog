package com.wilson404.blog.config;

import com.wilson404.blog.security.BlogUserDetailsService;
import com.wilson404.blog.security.DemoSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BlogUserDetailsService blogUserDetailsService;

    private final DemoSuccessHandler demoSuccessHandler;

    @Autowired
    public SecurityConfig(BlogUserDetailsService blogUserDetailsService, DemoSuccessHandler demoSuccessHandler) {
        this.blogUserDetailsService = blogUserDetailsService;
        this.demoSuccessHandler = demoSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.userDetailsService(blogUserDetailsService)
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll().successHandler(demoSuccessHandler)
                .and()
                .csrf().disable();


//        http.authorizeRequests().antMatchers("/hasLogin.do").anonymous();
//        http.addFilterBefore(null,UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
