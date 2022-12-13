package com.hefnawy.DoneByToday.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] PUBLIC_ENDPOINTS = {"/auth/**"};

    @Autowired
    private AuthFilter authFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

       /*auth.inMemoryAuthentication()
               .withUser("admin")
               .password("{noop}pass")
               .authorities("ADMIN_ROLE")
               .and()
               .withUser("user")
               .password("{noop}12345")
               .authorities("USER_ROLE");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("") // Query to get all the userNames
                .authoritiesByUsernameQuery(""); // Query to get the authorities of the users*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/todos/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/users/logout").hasRole("USER");
        http.authorizeRequests().antMatchers("/users/**").hasRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(PUBLIC_ENDPOINTS);
    }

    @Bean
    AuthenticationManager authManager() throws Exception {
        return super.authenticationManager();
    }
}
