package com.anhfuentes.concertcapstone.config;

import com.anhfuentes.concertcapstone.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                // Admin-only pages
                                .requestMatchers("/concerts/add", "/concert/edit", "/bookings/edit", "/users/**").hasRole("ADMIN")
                                // Both users and admins
                                .requestMatchers("/login", "/register", "/userProfile", "/index").permitAll()
                                // Anyone can view index
                                .requestMatchers("/").permitAll()
                                // All other requests require authentication
                                .anyRequest().authenticated()
                )
                .formLogin(
                        formLogin ->
                                formLogin
                                        .loginPage("/login")
                                        .permitAll()
                )
                .logout(
                        logout ->
                                logout
                                        .invalidateHttpSession(true)
                                        .clearAuthentication(true)
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                        .logoutSuccessUrl("/login?logout")
                                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

}
