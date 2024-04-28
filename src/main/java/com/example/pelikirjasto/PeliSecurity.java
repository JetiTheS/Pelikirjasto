package com.example.pelikirjasto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.pelikirjasto.web.PeliUserDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class PeliSecurity {
        @Autowired
        private PeliUserDetailService userDetailService;

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http

                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(("/css/**")).permitAll()
                                                .requestMatchers("/", "/pelikirjasto").permitAll()
                                                .anyRequest().authenticated())

                                .formLogin(formlogin -> formlogin.loginPage("/login")
                                                .defaultSuccessUrl("/pelikirjasto", true)
                                                .permitAll())
                                .logout((logout) -> logout
                                                .logoutSuccessUrl("/pelikirjasto")
                                                .permitAll());
                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
        }
}
