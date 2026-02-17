package com.proyecto.amieva.configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
            .requestMatchers("/login").permitAll()
            .requestMatchers("/index","/home","/").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/home/**", "/index/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers(PathRequest.toH2Console()).hasRole("ADMIN")
            .requestMatchers("/h2-console/**", "/h2/**").hasRole("ADMIN")
            .requestMatchers("/index", "/editar/**", "/eliminar/**", "/administracion").hasRole("ADMIN")
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

    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("user1"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }
}