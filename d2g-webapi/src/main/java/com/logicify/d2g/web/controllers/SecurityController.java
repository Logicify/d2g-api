package com.logicify.d2g.web.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by jadencorr on 06.03.17.
 */

@RestController
@EnableWebSecurity
public class SecurityController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }


    @Configuration
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/login", "/").permitAll()
                    .anyRequest().authenticated().and()
                    .httpBasic()
                    .and()
                    .csrf().disable()
            ;

        }
    }


}