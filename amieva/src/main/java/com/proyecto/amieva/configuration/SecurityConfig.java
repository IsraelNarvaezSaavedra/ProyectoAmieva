package com.proyecto.amieva.configuration;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http.headers(headers -> 
            headers.frameOptions(frame -> frame.sameOrigin())
        );

        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .requestMatchers("/login", "/logout","/h2/**").permitAll()
            .requestMatchers("/index","/home","/").hasAnyRole("DIRECTIVA", "PROFESOR")
            .requestMatchers("/home/**", "/index/**").hasAnyRole("DIRECTIVA", "PROFESOR")
            .requestMatchers("/alumnos", "/alumnos/**").hasAnyRole("DIRECTIVA", "PROFESOR")
            .requestMatchers(PathRequest.toH2Console()).hasRole("DIRECTIVA")
            .requestMatchers("/h2-console/**", "/h2/**").hasRole("DIRECTIVA")
            .anyRequest().authenticated()
        );

        http.csrf(csrf -> csrf
            .ignoringRequestMatchers(PathRequest.toH2Console())
            .ignoringRequestMatchers("/h2-console/**", "/h2/**")
        );

        http.formLogin(form -> form
        	.loginPage("/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
        );

        http.logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}