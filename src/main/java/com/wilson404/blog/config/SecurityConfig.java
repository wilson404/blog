package com.wilson404.blog.config;

import com.wilson404.blog.security.BlogUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BlogUserDetailsService blogUserDetailsService;

    @Autowired
    public SecurityConfig(BlogUserDetailsService blogUserDetailsService) {
        this.blogUserDetailsService = blogUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.userDetailsService(blogUserDetailsService);
    }
}
