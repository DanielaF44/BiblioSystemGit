package com.example.bibliosystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll()
//                .authorizeRequests()
//                .antMatchers("/livres").anonymous()
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .antMatchers("/user").permitAll()
//                .anyRequest().anonymous()
                //.and()
                //.formLogin()
                .and()
                .httpBasic()
        ;
        http.cors();
}

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


}
