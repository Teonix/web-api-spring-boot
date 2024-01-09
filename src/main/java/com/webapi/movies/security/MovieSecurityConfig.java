package com.webapi.movies.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class MovieSecurityConfig {

    // Users and roles stored in application.

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails teo = User.builder()
//                .username("teo")
//                .password("{bcrypt}$2a$10$v34bqFnFD95.2NBeGBZzEuTrM5yat.gx8tuZr1J98/Ru56DuUbPpa")
//                .roles("USER")
//                .build();
//
//        UserDetails ben = User.builder()
//                .username("ben")
//                .password("{bcrypt}$2a$10$v34bqFnFD95.2NBeGBZzEuTrM5yat.gx8tuZr1J98/Ru56DuUbPpa")
//                .roles("USER", "MANAGER")
//                .build();
//
//        UserDetails kelly = User.builder()
//                .username("kelly")
//                .password("{bcrypt}$2a$10$v34bqFnFD95.2NBeGBZzEuTrM5yat.gx8tuZr1J98/Ru56DuUbPpa")
//                .roles("USER", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(teo, ben, kelly);
//    }


    // Users and roles stored in database.

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//
//        return new JdbcUserDetailsManager(dataSource);
//    }


    // Users and roles stored in database with custom tables.

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, active from users where username = ?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, role from roles where username = ?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/movies/paged").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/movies").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/movies/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/movies").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/movies/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/movies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/movies").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();

    }
}
